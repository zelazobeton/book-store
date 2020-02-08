package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.CartItem;
import com.zelazobeton.bookstore.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class CartItemCommand {
    private Long id;
    @Min(value = 1, message = "Amount of items must be a positive integer")
    private Integer amount;
    private Item item;
    private Boolean removeFromCart;

    public CartItemCommand() {
        this.removeFromCart = false;
    }

    public CartItemCommand(Item item) {
        this.amount = 1;
        this.item = item;
        this.removeFromCart = false;
    }

    public CartItemCommand(CartItem cartItem) {
        this.id = cartItem.getId();
        this.amount = cartItem.getAmount();
        this.item = cartItem.getItem();
        this.removeFromCart = false;
    }

    public Boolean removeFromCart()
    {
        return removeFromCart;
    }
}
