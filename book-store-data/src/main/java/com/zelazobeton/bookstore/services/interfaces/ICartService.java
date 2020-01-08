package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.commands.CartCommand;
import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.model.User;

public interface ICartService {
//    Cart addToCart(Cart cart, CartObjectCommand command);
    Cart addToCartByUser(User user, CartItemCommand command);
    Cart updateCart(User user, CartCommand command);
    Cart getCartByUser(User user);

}
