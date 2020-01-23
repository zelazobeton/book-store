package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.commands.ItemCommand;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.services.interfaces.IAdminService;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IImageService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final IAdminService adminService;
    private final IItemService itemService;
    private final ICategoryService categoryService;
    private final IImageService imageService;

    public AdminController(IAdminService adminService, IItemService itemService, ICategoryService categoryService, IImageService imageService) {
        this.adminService = adminService;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    @GetMapping("/console")
    public String getConsole(Model model){
        model.addAttribute("functions", adminService.getFunctions());
        return "console";
    }

    @GetMapping("/add-item")
    public String getAddItemSite(Model model){
        model.addAttribute("functions", adminService.getFunctions());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("item", new ItemCommand());
        return "add_item";
    }

    @PostMapping("/add-item")
    public String saveNewItem(@Valid @ModelAttribute("item") ItemCommand command,
                              BindingResult result,
                              Model model)
    {
        if(result.hasErrors()){
            System.out.println("@ item form has errors");
            result.getAllErrors().forEach(System.out::println);
            model.addAttribute("recipe", command);
            return "add_item";
        }
        Item newItem = itemService.save(command.build());
        imageService.saveImageFile(newItem.getId(), command.getMultipartImageFile());
        model.addAttribute("functions", adminService.getFunctions());
        System.out.println("@ new item added");
        return "redirect:/item/" + newItem.getId();
    }

    @GetMapping("item={id}/add-img")
    public String showUploadForm(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        model.addAttribute("functions", adminService.getFunctions());
        return "recipe/add-img";
    }

    @PostMapping("item={id}/add-img")
    public String addImgToDb(@PathVariable("id") Long id, @RequestParam("imagefile") MultipartFile file) {
        System.out.println("@ addImgToDb()");
        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/item/" + id + "/img=0";
    }
}
