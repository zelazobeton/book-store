package com.zelazobeton.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CartObject extends BaseEntity{
    private Integer amount;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Item item;
    @ManyToOne
    private Cart cart;
    public CartObject() {}
    public CartObject(Integer amount, Item item) {
        this.amount = amount;
        this.item = item;
    }
    public CartObject(Item item) {
        this(1, item);
    }

    public void addAmount(Integer toAdd){
        amount += toAdd;
    }
}
