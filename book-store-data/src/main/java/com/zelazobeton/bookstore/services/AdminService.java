package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.repository.UserRepository;
import com.zelazobeton.bookstore.services.interfaces.IAdminService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements IAdminService {
    private UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }
}
