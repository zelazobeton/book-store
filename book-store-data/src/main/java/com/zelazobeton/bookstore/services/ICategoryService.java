package com.zelazobeton.bookstore.services;

import com.sun.xml.bind.v2.model.core.ID;
import com.zelazobeton.bookstore.model.Category;

import java.util.Set;

public interface ICategoryService {
    Category findById(Long id);
    Category save(Category object);
    Set<Category> findAll();

    void delete(Category object);
    void deleteById(Long id);
}
