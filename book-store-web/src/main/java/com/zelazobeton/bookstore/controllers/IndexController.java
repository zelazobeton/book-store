package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.zelazobeton.bookstore.model.Category;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
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
        List<Category> categories = categoryService.findAll();
        Set<Item> items = itemService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("/category/{id}")
    public String getItemsByCategory(Model model, @PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if(category == null){
            return "redirect:/";
        }
        List<Category> categories = categoryService.findAll();
        List<Item> items = itemService.findByCategory(category);
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("/items/{id}")
    public String getItemDetailView(Model model, @PathVariable("id") Long id){
        Item item = itemService.findById(id);
        if(item == null){
            return "redirect:/";
        }
        model.addAttribute("item", item);
        return "item_detail_view";
    }
}
