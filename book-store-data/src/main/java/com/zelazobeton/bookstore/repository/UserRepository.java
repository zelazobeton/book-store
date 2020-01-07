package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // fetch type for authorities is set to LAZY, so they won't be
    // loaded in the user object unless we specifically order them to be
    // we want them to be loaded when we get user with this method
    @Query("select u from User u "
            + " left join fetch u.authorities"
            + " where u.username = :username")
    User findByUsername(String username);


}
