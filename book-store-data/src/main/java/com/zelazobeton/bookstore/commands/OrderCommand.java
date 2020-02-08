package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.CartItem;
import com.zelazobeton.bookstore.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderCommand {
    private Long id;
    @Valid
    private List<@Valid CartItemCommand> orderItems = new ArrayList<>();
    private User user;
    @Valid
    private AddressCommand addressCommand;

    public boolean isEmpty(){
        return orderItems.isEmpty();
    }

    public OrderCommand() {}
    public OrderCommand(Cart cart) {
        this.id = cart.getId();
        this.user = cart.getUser();
        for (CartItem obj : cart.getCartItems()) {
            orderItems.add(new CartItemCommand(obj));
        }
        addressCommand = new AddressCommand(cart.getUser());
    }
}
