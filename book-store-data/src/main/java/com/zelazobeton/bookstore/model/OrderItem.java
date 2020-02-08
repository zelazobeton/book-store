package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.CartItemCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class OrderItem extends TokenItem{
    @ManyToOne
    private UserOrder userOrder;
    public OrderItem() {}

    public OrderItem(CartItemCommand command, UserOrder userOrder){
        super(command);
        setUserOrder(userOrder);
    }
}
