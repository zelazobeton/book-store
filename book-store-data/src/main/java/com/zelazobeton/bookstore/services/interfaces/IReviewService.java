package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.commands.ReviewCommand;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Review;

import java.util.List;

public interface IReviewService {
    void save(ReviewCommand object);

    void delete(Review object);
    void deleteById(Long id);
}
