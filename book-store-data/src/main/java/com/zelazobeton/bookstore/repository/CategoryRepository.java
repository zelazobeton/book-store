package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
