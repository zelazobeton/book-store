package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
