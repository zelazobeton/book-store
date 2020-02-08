package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.commands.OrderCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserOrder extends BaseEntity {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userOrder")
    List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public UserOrder() {}
    public UserOrder(OrderCommand orderCommand)
    {
        List<CartItemCommand> cartItemCommands = orderCommand.getOrderItems();
        for(CartItemCommand elem : cartItemCommands){
            addToOrder(new OrderItem(elem, this));
        }
        this.user = orderCommand.getUser();
//        //TODO: DOES USER_ORDER HAVE TO BE SET IN ADDRESS?
//        orderCommand.getAddressCommand().setUserOrder(this);
//        this.address = new Address(orderCommand.getAddressCommand());
    }

    public void addToOrder(OrderItem orderItem){
        orderItems.add(orderItem);
    }
}
