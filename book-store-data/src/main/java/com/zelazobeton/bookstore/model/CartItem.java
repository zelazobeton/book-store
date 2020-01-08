package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.CartItemCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CartItem extends BaseEntity{
    private Integer amount;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Item item;
    @ManyToOne
    private Cart cart;
    public CartItem() {}
    public CartItem(Integer amount, Item item, Cart cart) {
        this.amount = amount;
        this.item = item;
        this.cart = cart;
    }

    public CartItem(Item item, Cart cart) {
        this(1, item, cart);
    }

    public CartItem(CartItemCommand command){
        this.amount = command.getAmount();
        this.item = command.getItem();
    }

    public void addAmount(Integer toAdd){
        amount += toAdd;
    }
}
