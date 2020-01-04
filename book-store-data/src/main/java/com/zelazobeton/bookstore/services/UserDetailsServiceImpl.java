package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.SecurityUser;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username and/or password was incorrect.");
        }
        return new SecurityUser(user);
    }
}
