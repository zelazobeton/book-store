package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.model.User;

import java.util.List;

public interface IAdminService {
    List<User> getAllUsers();
}
