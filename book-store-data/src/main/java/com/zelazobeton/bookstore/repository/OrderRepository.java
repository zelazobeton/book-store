package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.UserOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<UserOrder, Long> {
}
