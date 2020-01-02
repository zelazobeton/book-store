package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.commands.ReviewCommand;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.Review;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.repository.ReviewRepository;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import com.zelazobeton.bookstore.services.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    private ReviewRepository reviewRepository;
    private ItemRepository itemRepository;

    public ReviewService(ReviewRepository reviewRepository, ItemRepository itemRepository) {
        this.reviewRepository = reviewRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Review save(ReviewCommand command) {
        Optional<Item> item = itemRepository.findById(command.item.getId());
        if(!item.isPresent()){
            return null;
        }
        Review savedReview = reviewRepository.save(new Review(command));
        item.get().addReview(savedReview);
        return savedReview;
    }

    @Override
    public void delete(Review object) {
        reviewRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

}
