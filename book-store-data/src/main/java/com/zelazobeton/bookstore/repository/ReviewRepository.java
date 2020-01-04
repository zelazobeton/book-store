package com.zelazobeton.bookstore.repository;

import com.zelazobeton.bookstore.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
