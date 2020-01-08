package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.CartItem;
import com.zelazobeton.bookstore.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartCommand {
    private Long id;
    private List<CartItemCommand> cartItems = new ArrayList<>();
    private User user;

    public CartCommand() {}
    public CartCommand(Cart cart) {
        this.id = cart.getId();
        this.user = cart.getUser();
        for (CartItem obj : cart.getCartItems()) {
            cartItems.add(new CartItemCommand(obj));
        }
    }
}
