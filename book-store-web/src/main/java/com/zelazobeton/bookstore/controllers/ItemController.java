package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.commands.ReviewCommand;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import com.zelazobeton.bookstore.services.interfaces.IReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    private final IReviewService reviewService;
    private final IItemService itemService;

    public ItemController(IReviewService reviewService, IItemService itemService) {
        this.reviewService = reviewService;
        this.itemService = itemService;
    }

    @GetMapping({"/item/{id}", "/item/{id}/"})
    public String getItemDetailView(Model model,
                                    @PathVariable("id") long id,
                                    @AuthenticationPrincipal User user){
        System.out.println("@@@ getItemDetailView");
        Item item = itemService.findById(id);
        model.addAttribute("reviewCommand", new ReviewCommand(item));
        if(!model.containsAttribute("cartItemCommand"))
        {
            model.addAttribute("cartItemCommand", new CartItemCommand(item));
        }
        model.addAttribute("item", item);
        model.addAttribute("user", user);
        return Templates.ITEM_DETAIL_VIEW;
    }

    @PostMapping("/item/{id}/review-update")
    public String updateItemReviews(Model model,
                                    @PathVariable("id") long id,
                                    @ModelAttribute ReviewCommand command,
                                    @AuthenticationPrincipal User user){
        command.setUser(user);
        reviewService.save(command);
        return "redirect:/item/" + id;
    }
}
