package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.CartCommand;
import com.zelazobeton.bookstore.commands.CartItemCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart extends BaseEntity {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<>();
    @OneToOne
    private User user;

    public Cart() {}

    public Cart(User user) {
        this.user = user;
    }

    public void addToCart(CartItem cartItem){
        cartItem.setCart(this);
        cartItems.add(cartItem);
    }

    public void updateByCommand(CartCommand command){
        List<CartItem> newList = new ArrayList<>();
        for(CartItemCommand elem : command.getCartItems()){
            if(!elem.removeFromCart())
            {
                CartItem cartItem = new CartItem(elem);
                cartItem.setCart(this);
                newList.add(cartItem);
            }
        }
        cartItems = newList;
    }

    public void removeItems(){
        List<CartItem> newList = new ArrayList<>();
        cartItems = newList;
    }

}
