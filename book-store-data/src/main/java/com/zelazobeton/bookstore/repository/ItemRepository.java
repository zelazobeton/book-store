package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.security.acl.Owner;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByCategories(Category category);
}
