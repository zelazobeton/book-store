package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;

import java.util.Set;

public interface IItemService {
    Item findById(Long id);
    Item save(Item object);
    Set<Item> findAll();

    void delete(Item object);
    void deleteById(Long id);
}
