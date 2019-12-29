package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.services.CategoryService;
import com.zelazobeton.bookstore.services.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.zelazobeton.bookstore.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {
    private final ICategoryService categoryService;

    public IndexController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String getIndexPage(Model model){
        Set<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "index";
    }
}
