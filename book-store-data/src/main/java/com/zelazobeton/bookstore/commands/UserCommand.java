package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.configuration.MatchingPassword;
import com.zelazobeton.bookstore.configuration.ValidPassword;
import com.zelazobeton.bookstore.model.Authorities;
import com.zelazobeton.bookstore.model.BaseEntity;
import com.zelazobeton.bookstore.model.Cart;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@MatchingPassword
public class UserCommand extends BaseEntity {
    @Email
    @NotNull
    private String username;

    @ValidPassword
    private String password;
    private String matchingPassword;

    private Cart cart;
    private Set<Authorities> authorities = new HashSet<>();
}