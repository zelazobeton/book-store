package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.commands.AddressCommand;
import com.zelazobeton.bookstore.commands.OrderCommand;
import com.zelazobeton.bookstore.exceptions.ResourceNotFoundException;
import com.zelazobeton.bookstore.model.Address;
import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.model.UserOrder;
import com.zelazobeton.bookstore.repository.*;
import com.zelazobeton.bookstore.services.interfaces.IOrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class OrderService implements IOrderService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;
    private AddressRepository addressRepository;

    public OrderService(CartRepository cartRepository,
                        CartItemRepository cartItemRepository,
                        UserRepository userRepository,
                        ItemRepository itemRepository,
                        OrderRepository orderRepository,
                        AddressRepository addressRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public UserOrder placeNewOrder(OrderCommand command, User user) {
        UserOrder newUserOrder = new UserOrder(command);
        AddressCommand newAddressCommand = command.getAddressCommand();
        if(newAddressCommand.isDefaultAddress()){
            Address defaultAddress = findDefaultAddressByUser(user);
            if(defaultAddress != null){
                defaultAddress.updateByAddressCommand(newAddressCommand);
                addressRepository.save(defaultAddress);
            }
            else{
                addressRepository.save(new Address(newAddressCommand));
            }
            newAddressCommand.setDefaultAddress(false);
        }
        newUserOrder.setAddress(new Address(newAddressCommand));
        UserOrder savedUserOrder = orderRepository.save(newUserOrder);
        removeOrderedItemsFromCart(user);
        return savedUserOrder;
    }

    private Address findDefaultAddressByUser(User user){
        return addressRepository.findByUserAndDefaultAddress(user, true);
    }

    public OrderCommand createOrderCommand(Cart cart){
        Address savedAddress = findDefaultAddressByUser(cart.getUser());
        AddressCommand defaultAddressCommand = (savedAddress == null)
                ? new AddressCommand(cart.getUser())
                : new AddressCommand(savedAddress);
        return new OrderCommand(cart, defaultAddressCommand);
    }

    @Override
    public List<UserOrder> getAllOrders(){
        List<UserOrder> orders = new ArrayList<>();
        StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .sorted(new UserOrder.CreationDateComparator())
                .forEach(orders::add);
        return orders;
    }

    @Override
    public UserOrder getOrderById(Long id) {
        if(id == null){
            return null;
        }
        Optional<UserOrder> orderOpt = orderRepository.findById(id);
        if(!orderOpt.isPresent()){
            return null;
        }
        return orderOpt.get();
    }

    private void removeOrderedItemsFromCart(User user){
        Optional<Cart> cartOpt = cartRepository.findByUser(user);
        if(cartOpt.isPresent()){
            Cart cartToEmpty = cartOpt.get();
            cartItemRepository.deleteAllByCart(cartToEmpty);
            cartToEmpty.removeItems();
            cartRepository.save(cartToEmpty);
        }
        else{
            throw new ResourceNotFoundException(
                    "User: " + user.getId() + " does not have cart to empty");
        }
    }
}
