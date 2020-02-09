package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.CartItemCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public class TokenItem extends BaseEntity{
    private Integer amount;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Item item;

    public TokenItem() {}
    public TokenItem(Integer amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public TokenItem(CartItemCommand command){
        this.amount = command.getAmount();
        this.item = command.getItem();
    }

    public void addAmount(Integer toAdd){
        amount += toAdd;
        if(amount > 10){amount = 10;}
    }
}
