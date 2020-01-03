package com.zelazobeton.bookstore.services.interfaces;

import com.sun.xml.bind.v2.model.core.ID;
import com.zelazobeton.bookstore.model.Category;

import java.util.List;
import java.util.Set;

public interface ICategoryService {
    Category findById(Long id);
    Category findByName(String name);
    Category save(Category object);
    List<Category> findAll();
    String getCategoryChainString(Category category);

    void delete(Category object);
    void deleteById(Long id);
}
