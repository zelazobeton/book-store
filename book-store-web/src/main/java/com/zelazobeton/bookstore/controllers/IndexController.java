package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.zelazobeton.bookstore.model.Category;

import java.util.Set;

@Controller
public class IndexController {
    private final ICategoryService categoryService;
    private final IItemService itemService;

    public IndexController(ICategoryService categoryService, IItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @GetMapping({"/", ""})
    public String getIndexPage(Model model){
        Set<Category> categories = categoryService.findAll();
        Set<Item> items = itemService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return "index";
    }
}
