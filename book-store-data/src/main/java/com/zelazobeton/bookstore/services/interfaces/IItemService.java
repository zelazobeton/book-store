package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;

import java.util.List;

public interface IItemService {
    Item findById(Long id);
    List<Item> findByCategory(Category category);
    List<Item> findByName(String name);
    Item save(Item object);
    List<Item> findAll();

    void delete(Item object);
    void deleteById(Long id);
}
