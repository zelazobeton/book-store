package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByCategories(Category category);

    @Query(nativeQuery=true, value="SELECT * FROM item WHERE name REGEXP ?1 OR description_full REGEXP ?1")
    List<Item> findByName(String nameRegEx);
}
