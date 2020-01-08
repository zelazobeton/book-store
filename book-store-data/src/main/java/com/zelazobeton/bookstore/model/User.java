package com.zelazobeton.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends BaseEntity {
    private String username;
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Authorities> authorities = new HashSet<>();

    @Override
    public String toString() {
        return username;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}