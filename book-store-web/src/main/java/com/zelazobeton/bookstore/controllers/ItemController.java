package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.ReviewCommand;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.Review;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import com.zelazobeton.bookstore.services.interfaces.IReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
public class ItemController {
    private final IReviewService reviewService;
    private final IItemService itemService;

    public ItemController(IReviewService reviewService, IItemService itemService) {
        this.reviewService = reviewService;
        this.itemService = itemService;
    }

    @GetMapping("/item**")
    public String getItemDetailView(Model model,
                                    @RequestParam("id") Long id,
                                    @AuthenticationPrincipal User user){
        System.out.println("@@@ getItemDetailView");
        Item item = itemService.findById(id);
        if(item == null){
            return "redirect:/";
        }
        model.addAttribute("command", new ReviewCommand(item));
        model.addAttribute("item", item);
        model.addAttribute("user", user);
        return Templates.ITEM_DETAIL_VIEW;
    }

//    @PostMapping("**/user**/item={id}/review-update")
    @PostMapping("/item/{id}/review-update")
    public String updateItemReviews(Model model,
                                    @PathVariable("id") long id,
                                    @ModelAttribute ReviewCommand command,
                                    @AuthenticationPrincipal User user,
                                    RedirectAttributes attributes){
        Item item = itemService.findById(id);
        if(item == null){
            return "redirect:/";
        }
        command.setDate(LocalDate.now());
        command.setAuthor(user);
        Review savedReview = reviewService.save(command);
        model.addAttribute("user", user);

        attributes.addAttribute("id", savedReview.getItem().getId());
        return "redirect:/item";
    }
}
