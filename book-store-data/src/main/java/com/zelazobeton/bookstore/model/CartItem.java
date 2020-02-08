package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.CartItemCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CartItem extends TokenItem{
    @ManyToOne
    private Cart cart;
    public CartItem() {}
    public CartItem(Integer amount, Item item, Cart cart) {
        super(amount, item);
        this.cart = cart;
    }

    public CartItem(Item item, Cart cart) {
        this(1, item, cart);
    }

    public CartItem(CartItemCommand command){
        super(command);
    }
}
