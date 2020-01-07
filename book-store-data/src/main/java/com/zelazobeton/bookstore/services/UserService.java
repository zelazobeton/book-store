package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.repository.UserRepository;
import com.zelazobeton.bookstore.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public Cart getUserCart(User user) {
//
//    }
}
