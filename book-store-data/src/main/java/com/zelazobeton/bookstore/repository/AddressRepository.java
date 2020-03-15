package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Address;
import com.zelazobeton.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Address findByUserAndSavedByUser(User user, boolean savedByUser);
}
