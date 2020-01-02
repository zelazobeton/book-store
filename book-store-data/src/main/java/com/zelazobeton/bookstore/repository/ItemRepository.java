package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
