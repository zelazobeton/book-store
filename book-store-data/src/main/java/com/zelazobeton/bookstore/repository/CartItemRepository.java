package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Cart;
import com.zelazobeton.bookstore.model.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    Set<CartItem> findAllByCart(Cart cart);
    void deleteAllByCart(Cart cart);
}
