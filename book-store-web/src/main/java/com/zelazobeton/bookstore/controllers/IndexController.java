package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class IndexController {
    private final ICategoryService categoryService;
    private final IItemService itemService;

    public IndexController(ICategoryService categoryService, IItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @GetMapping("/cat/{category:[a-zA-Z\\-]+}")
    public String getItemsByCategory(Model model,
                                     @PathVariable("category") String categoryName,
                                     @AuthenticationPrincipal User user){
        System.out.println("getItemsByCategory category: " + categoryName);
        Category category = categoryService.findByName(categoryName.replace('-', ' '));
        if(category == null){
            return "redirect:/";
        }

        List<Item> items = itemService.findByCategory(category);
        model.addAttribute("categories", category.getSubcategories());
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        return Templates.INDEX_VIEW;
    }

    @GetMapping({"/", "", "/cat"})
    public String getIndexPage(Model model,
                               @AuthenticationPrincipal User user){
        System.out.println("@Controller: getIndexPage()");
        List<Item> items = itemService.findAll();
        addBasicAttributesToModel(model, user, items, null);
        return Templates.INDEX_VIEW;
    }

    @GetMapping("/search")
    public String getItemsBySearchText(Model model,
                                       @RequestParam String s,
                                       @AuthenticationPrincipal User user){
        List<Item> items = itemService.findByName(s);
        addBasicAttributesToModel(model, user, items, s);
        return Templates.INDEX_VIEW;
    }

    private void addBasicAttributesToModel(Model model,
                                           User user,
                                           List<Item> items,
                                           String searchText){
        List<Category> categories = new ArrayList<>();
        categoryService.findAll()
                .stream()
                .filter(Category::isBasicCategory)
                .forEach(categories::add);
        model.addAttribute("categories", categories);
        model.addAttribute("user", user);
        model.addAttribute("items", items);

        if(searchText == ""){
            searchText = null;
        }
        model.addAttribute("search_text", searchText);
    }
}
