package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.commands.CartCommand;
import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.model.*;
import com.zelazobeton.bookstore.repository.CartItemRepository;
import com.zelazobeton.bookstore.repository.CartRepository;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.repository.UserRepository;
import com.zelazobeton.bookstore.services.interfaces.ICartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       UserRepository userRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    private Cart addToCart(Cart cart, CartItemCommand command)
    {
        Optional<Item> optItem = itemRepository.findById(command.getItem().getId());
        if(!optItem.isPresent() || command.getAmount() < 1){
            return cart;
        }
        Item item = optItem.get();
        Optional<CartItem> objInCart = cart
                .getCartItems()
                .stream()
                .filter(obj -> obj.getItem().getId().equals(item.getId()))
                .findFirst();
        if(objInCart.isPresent()){
            objInCart.get().addAmount(command.getAmount());
        }
        else{
            cart.addToCart(new CartItem(command.getAmount(), item, cart));
        }
        Cart savedCart = cartRepository.save(cart);
        return savedCart;
    }

    @Override
    public Cart addToCartByUser(User user, CartItemCommand command) {
        Cart cart = getCartByUser(user);
        return addToCart(cart, command);
    }

    @Override
    @Transactional
    public Cart updateCart(User user, CartCommand command)
    {
        Cart cart = getCartByUser(user);
        cartItemRepository.deleteAllByCart(cart);
        cart.updateByCommand(command);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deleteCartItemByCart(Cart cart){
        cartItemRepository.deleteAllByCart(cart);
    }

    @Override
    public Cart getCartByUser(User user) {
        System.out.println("@ getCartByUser()");
        Optional<Cart> optCart = cartRepository.findByUser(user);
        if (optCart.isPresent()) {
            return optCart.get();
        }
        return createNewCartForUser(user);
    }

    private Cart createNewCartForUser(User user){
        Optional<User> userFromDbOpt = userRepository.findById(user.getId());
        if(userFromDbOpt.isPresent()){
            User userFromDb = userFromDbOpt.get();
            Cart newCart = new Cart(userFromDb);
            userFromDb.setCart(newCart);
            User savedUser = userRepository.save(userFromDb);
            return cartRepository.findByUser(savedUser).get();
        }
        System.out.println("No user: " + user.getUsername() + " in db");
        return null;
    }
}
