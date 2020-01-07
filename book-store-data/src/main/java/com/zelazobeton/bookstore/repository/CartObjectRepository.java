package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.CartObject;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CartObjectRepository extends CrudRepository<CartObject, Long> {
    Set<CartObject> findAllByCart(Cart cart);
}
