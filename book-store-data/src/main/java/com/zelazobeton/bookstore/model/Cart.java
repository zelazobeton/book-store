package com.zelazobeton.bookstore.model;

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
    List<CartObject> cartObjects = new ArrayList<>();
    @OneToOne
    private User user;

    public Cart() {}

    public void addToCart(CartObject cartObject){
        cartObject.setCart(this);
        cartObjects.add(cartObject);
    }

    public void update(Cart cart){
        cartObjects = cart.getCartObjects();
    }

}
