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

    @Override
    public List<Function> getFunctions() {
        List<Function> functions = new ArrayList<>();
        functions.add(new Function("Add item"));
        functions.add(new Function("Add category"));
        functions.add(new Function("Manage users"));
        return functions;
    }

    public class Function{
        public String name;
        public String link;
        public Function(String name) {
            this.name = name;
            this.link = name.toLowerCase().replace(' ', '-');
        }
    }
}
