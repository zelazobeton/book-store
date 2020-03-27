package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.commands.OrderCommand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@Setter
public class UserOrder extends BaseEntity {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userOrder")
    List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne
    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    public UserOrder() {}
    public UserOrder(OrderCommand orderCommand)
    {
        List<CartItemCommand> cartItemCommands = orderCommand.getOrderItems();
        for(CartItemCommand elem : cartItemCommands){
            addToOrder(new OrderItem(elem, this));
        }
        this.user = orderCommand.getUser();
        this.creationDate = LocalDate.now();
        this.address = new Address(orderCommand.getAddressCommand());
    }

    public void addToOrder(OrderItem orderItem){
        orderItems.add(orderItem);
    }

    public static class CreationDateComparator implements Comparator<UserOrder>{
        @Override
        public int compare(UserOrder o1, UserOrder o2) {
            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }
    }

    public double getOrderPrice(){
        double price = 0;
        for(OrderItem o : orderItems){
            price += o.getTotalPrice();
        }
        return price;
    }
}
