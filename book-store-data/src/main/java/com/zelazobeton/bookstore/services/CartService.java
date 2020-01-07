package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.*;
import com.zelazobeton.bookstore.repository.CartObjectRepository;
import com.zelazobeton.bookstore.repository.CartRepository;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.repository.UserRepository;
import com.zelazobeton.bookstore.services.interfaces.ICartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService {
    private CartRepository cartRepository;
    private CartObjectRepository cartObjectRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, CartObjectRepository cartObjectRepository,
                       UserRepository userRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.cartObjectRepository = cartObjectRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Cart addToCart(Cart cart, CartObjectCommand command)
    {
        System.out.println("@ addToCart 0");
        Optional<Item> optItem = itemRepository.findById(command.getItemId());
        if(!optItem.isPresent() || command.getAmount() < 1){
            System.out.println("@ addToCart 1");
            return cart;
        }
        Item item = optItem.get();
        Optional<CartObject> objInCart = cart
                .getCartObjects()
                .stream()
                .filter(obj -> obj.getItem().getId().equals(item.getId()))
                .findFirst();
        if(objInCart.isPresent()){
            objInCart.get().addAmount(command.getAmount());
        }
        else{
            cart.addToCart(new CartObject(command.getAmount(), item));
        }
        System.out.println("@ addToCart 2");
        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCartByUser(User user, CartObjectCommand command) {
        Cart cart = getCartByUser(user);
        return addToCart(cart, command);
    }

    @Override
    public Cart updateCart(User user, Cart cart)
    {
        Optional<Cart> optOldCart = cartRepository.findByUser(user);
        if(optOldCart.isPresent()){
            Cart oldCart = optOldCart.get();
            oldCart.update(cart);
            return cartRepository.save(oldCart);
        }
        cart.setUser(user);
        Cart savedNewCart = cartRepository.save(cart);
        user.setCart(savedNewCart);
        return savedNewCart;
    }

    @Override
    public Cart getCartByUser(User user) {
        System.out.println("@ getCartByUser 1");
        Optional<Cart> optCart = cartRepository.findByUser(user);
        System.out.println("@ getCartByUser 2");
        if (optCart.isPresent()) {
            System.out.println("@ getCartByUser 3");
            return optCart.get();
        }
        System.out.println("@ getCartByUser 4");
        Cart newCart = new Cart();
        newCart.setUser(user);
//        user.setCart(new Cart());
//        User savedUser = userRepository.save(user);
//        User savedUser = userRepository.save(user);
//        return savedUser.getCart();
        return cartRepository.save(newCart);
    }
}
