package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.commands.OrderCommand;
import com.zelazobeton.bookstore.model.*;
import com.zelazobeton.bookstore.repository.*;
import com.zelazobeton.bookstore.services.interfaces.IOrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public UserOrder saveOrderByCommand(OrderCommand command, User user) {
        UserOrder newUserOrder = new UserOrder(command);
        Address newAddress = new Address(command.getAddressCommand());
        newUserOrder.setAddress(newAddress);
        newAddress.setUserOrder(newUserOrder);
        UserOrder savedUserOrder = orderRepository.save(newUserOrder);
        removeOrderedItemsFromCart(user);
        return savedUserOrder;
    }

    private void removeOrderedItemsFromCart(User user){
        Optional<Cart> cartOpt = cartRepository.findByUser(user);
        if(cartOpt.isPresent()){
            Cart cartToEmpty = cartOpt.get();
            cartItemRepository.deleteAllByCart(cartToEmpty);
            cartToEmpty.removeItems();
            cartRepository.save(cartToEmpty);
        }
    }
}
