package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.CartObject;
import com.zelazobeton.bookstore.model.CartObjectCommand;
import com.zelazobeton.bookstore.model.User;

import java.util.Optional;

public interface ICartService {
    Cart addToCart(Cart cart, CartObjectCommand command);
    Cart addToCartByUser(User user, CartObjectCommand command);
    Cart updateCart(User user, Cart cart);
    Cart getCartByUser(User user);

}
