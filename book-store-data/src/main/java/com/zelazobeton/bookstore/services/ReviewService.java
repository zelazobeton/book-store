package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.commands.ReviewCommand;
import com.zelazobeton.bookstore.exceptions.ResourceNotFoundException;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.Review;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.repository.ReviewRepository;
import com.zelazobeton.bookstore.services.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public void save(ReviewCommand command) {
        Optional<Item> itemOpt = itemRepository.findById(command.getItem().getId());
        if(itemOpt.isPresent()){
            Item item = itemOpt.get();
            item.addReview(new Review(command));
            itemRepository.save(item);
        }
        else{
            throw new ResourceNotFoundException(
                    "Item with id: " + command.getItem().getId() + " does not exist");
        }
    }

    @Override
    @Transactional
    public void delete(Review object) {
        reviewRepository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

}
