package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.Authorities;
import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public class UserBuilder {
    private User user;

    public UserBuilder(UserCommand userCommand) {
        this.user = new User();
        Authorities authorities = new Authorities("ROLE_USER", user);
        this.user.setAuthorities(new HashSet<>());
        this.user.getAuthorities().add(authorities);
        this.user.setCart(new Cart(this.user));
        this.user.setUsername(userCommand.getUsername());

        String encodedPassword = (new BCryptPasswordEncoder()).encode(userCommand.getPassword());
        this.user.setPassword(encodedPassword);
    }

    public User build(){
        return user;
    }
}
