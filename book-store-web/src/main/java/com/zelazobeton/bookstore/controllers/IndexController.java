package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.zelazobeton.bookstore.model.Category;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@Slf4j
public class IndexController {
    private final ICategoryService categoryService;
    private final IItemService itemService;

    public IndexController(ICategoryService categoryService, IItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @GetMapping({"/{category:[a-zA-Z\\-]+}",
                 "/{firstCat}/{category:[a-zA-Z\\-]+}",
                 "/{firstCat}/{secondCat}/{category:[a-zA-Z\\-]+}"})
    public String getItemsByCategory(Model model,
                                     @PathVariable("category") String categoryName){
        System.out.println("getItemsByCategory category: " + categoryName);
        Category category = categoryService.findByName(categoryName.replace('-', ' '));
        if(category == null){
            return "redirect:/";
        }

        List<Item> items = itemService.findByCategory(category);
        model.addAttribute("categories", category.getSubcategories());
        model.addAttribute("items", items);
        return Templates.INDEX_VIEW;
    }

    @GetMapping({"/", ""})
    public String getIndexPage(Model model){
        List<Category> categories = new ArrayList<>();
        categoryService.findAll()
                       .stream()
                       .filter(Category::isBasicCategory)
                       .forEach(categories::add);
        Set<Item> items = itemService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return Templates.INDEX_VIEW;
    }
}
