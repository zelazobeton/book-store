package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.ItemCommand;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.interfaces.IAdminService;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IImageService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
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

    public AdminController(IAdminService adminService, IItemService itemService, ICategoryService categoryService, IImageService imageService) {
        this.adminService = adminService;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    @GetMapping("/console")
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

//    @GetMapping("item={id}/add-img")
//    public String showUploadForm(@PathVariable("id") Long id, Model model) {
//        Item item = itemService.findById(id);
//        model.addAttribute("item", item);
//        model.addAttribute("functions", adminService.getFunctions());
//        return "recipe/add-img";
//    }
//
//    @PostMapping("item={id}/add-img")
//    public String addImgToDb(@PathVariable("id") Long id,
//                             @RequestParam("imagefile") MultipartFile file)
//        throws IOException
//    {
//        System.out.println("@ addImgToDb()");
//        imageService.saveImageFile(Long.valueOf(id), file);
//        return "redirect:/item/" + id + "/img=0";
//    }

    private String backToItemForm(Model model, ItemCommand command){
        model.addAttribute("recipe", command);
        return Templates.ADD_UPDATE_ITEM;
    }
}
