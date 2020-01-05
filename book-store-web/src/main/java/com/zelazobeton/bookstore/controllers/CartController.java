package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.ItemService;
import com.zelazobeton.bookstore.services.interfaces.IUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
//    private final IUserService userService;
    private final ItemService itemService;

    public CartController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/cart")
    public String addItemToCart(@AuthenticationPrincipal User user){
        return Templates.CART_VIEW;
    }

    @PostMapping("/cart")
    public String addItemToCart(Model model,
                                @ModelAttribute Item newItem,
                                @AuthenticationPrincipal User user){
//        List<Item> items =
//        model.addAttribute("items", items);
        model.addAttribute("user", user);
        return Templates.CART_VIEW;
    }
}
