package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Address;
import com.zelazobeton.bookstore.model.UserOrder;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
