package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.CartItem;
import com.zelazobeton.bookstore.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class CartItemCommand{
    private Long id;
    private Integer amount;
    private Item item;

    public CartItemCommand() {}
    public CartItemCommand(Item item) {
        this.amount = 1;
        this.item = item;
    }

    public CartItemCommand(CartItem cartItem) {
        this.id = cartItem.getId();
        this.amount = cartItem.getAmount();
        this.item = cartItem.getItem();
    }
}
