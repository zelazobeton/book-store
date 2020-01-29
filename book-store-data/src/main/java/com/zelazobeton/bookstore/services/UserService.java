package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.commands.UserBuilder;
import com.zelazobeton.bookstore.commands.UserCommand;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.repository.UserRepository;
import com.zelazobeton.bookstore.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userExists(UserCommand userCommand){
        User user = userRepository.findByUsername(userCommand.getUsername());
        return user != null;
    }

    public void registerUser(UserCommand userCommand){
        User newUser = (new UserBuilder(userCommand)).build();
        userRepository.save(newUser);
    }
}
