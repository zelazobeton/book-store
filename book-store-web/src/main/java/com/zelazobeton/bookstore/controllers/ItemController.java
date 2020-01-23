package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.commands.ReviewCommand;
import com.zelazobeton.bookstore.model.*;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import com.zelazobeton.bookstore.services.interfaces.IReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

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
        if(item == null){
            return "redirect:/";
        }
        model.addAttribute("reviewCommand", new ReviewCommand(item));
        //TODO use only cartItemCommand
        model.addAttribute("cartItemCommand", new CartItemCommand(item));
        model.addAttribute("item", item);
        model.addAttribute("user", user);
        return Templates.ITEM_DETAIL_VIEW;
    }

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
        command.setUser(user);
        reviewService.save(command);
        return "redirect:/item/" + id;
    }
}
