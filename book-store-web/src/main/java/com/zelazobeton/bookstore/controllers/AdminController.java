package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.ItemCommand;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.interfaces.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final IAdminService adminService;
    private final IItemService itemService;
    private final ICategoryService categoryService;
    private final IImageService imageService;
    private final IOrderService orderService;

    public AdminController(IAdminService adminService,
                           IItemService itemService,
                           ICategoryService categoryService,
                           IImageService imageService,
                           IOrderService orderService) {
        this.adminService = adminService;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.orderService = orderService;
    }

    @GetMapping({"/", "/console"})
    public String getConsole(Model model,
                             @AuthenticationPrincipal User user){
        model.addAttribute("functions", adminService.getFunctions());
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("user", user);
        return Templates.CONSOLE;
    }

    @GetMapping("/add-item")
    public String getAddItemSite(Model model,
                                 @AuthenticationPrincipal User user){
        model.addAttribute("functions", adminService.getFunctions());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("item", new ItemCommand());
        model.addAttribute("user", user);
        return Templates.ADD_UPDATE_ITEM;
    }

    @GetMapping("/item/{id}/update")
    public String getUpdateItemSite(Model model,
                                    @PathVariable("id") long id,
                                    @AuthenticationPrincipal User user){
        model.addAttribute("functions", adminService.getFunctions());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("item", new ItemCommand(itemService.findById(id)));
        model.addAttribute("user", user);
        return Templates.ADD_UPDATE_ITEM;
    }

    @PostMapping({"add-update-item", "/item/{id}/add-update-item"})
    public String addOrUpdateItem(@Valid @ModelAttribute("item") ItemCommand command,
                                  BindingResult result,
                                  Model model,
                                  @AuthenticationPrincipal User user)
    {
        if(result.hasErrors()){
            result.getAllErrors().forEach(System.out::println);
            return backToItemForm(model, command);
        }
        Item savedItem = itemService.save(command.buildItem());

        try{
            imageService.saveImageFile(savedItem.getId(), command.getMultipartImageFile());
        }
        catch (IOException ex){
            return backToItemForm(model, command);
        }
        model.addAttribute("functions", adminService.getFunctions());
        model.addAttribute("user", user);
        return "redirect:/item/" + savedItem.getId();
    }

    @GetMapping("/show-orders")
    public String getAllOrders(Model model,
                            @AuthenticationPrincipal User user){
        model.addAttribute("orders", orderService.getAllOrders());
        return Templates.SHOW_ORDERS;
    }

    @GetMapping("/show-orders/{id}")
    public String getOrderDetail(Model model,
                            @PathVariable("id") long id,
                            @AuthenticationPrincipal User user){
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("order_detail", orderService.getOrderById(id));
        return Templates.SHOW_ORDERS;
    }

    private String backToItemForm(Model model, ItemCommand command){
        model.addAttribute("recipe", command);
        return Templates.ADD_UPDATE_ITEM;
    }
}
