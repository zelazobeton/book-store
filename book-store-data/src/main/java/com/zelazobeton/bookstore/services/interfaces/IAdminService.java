package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.AdminService;

import java.util.List;

public interface IAdminService {
    List<User> getAllUsers();
    List<AdminService.Function> getFunctions();
}
