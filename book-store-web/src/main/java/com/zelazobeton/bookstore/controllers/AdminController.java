package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.ItemCommand;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.services.interfaces.IAdminService;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import com.zelazobeton.bookstore.services.interfaces.IImageService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

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
        return Templates.CONSOLE;
    }

    @GetMapping("/add-item")
    public String getAddItemSite(Model model){
        model.addAttribute("functions", adminService.getFunctions());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("item", new ItemCommand());
        return Templates.ADD_ITEM;
    }

    private String backToAddItemForm(Model model, ItemCommand command){
        model.addAttribute("recipe", command);
        return Templates.ADD_ITEM;
    }

    @PostMapping("/add-item")
    public String saveNewItem(@Valid @ModelAttribute("item") ItemCommand command,
                              BindingResult result,
                              Model model)
    {
        if(result.hasErrors()){
            result.getAllErrors().forEach(System.out::println);
            return backToAddItemForm(model, command);
        }
        Item newItem = itemService.save(command.buildItem());

        try{
            imageService.saveImageFile(newItem.getId(), command.getMultipartImageFile());
        }
        catch (IOException ex){
            itemService.deleteById(newItem.getId());
            return backToAddItemForm(model, command);
        }
        model.addAttribute("functions", adminService.getFunctions());
        System.out.println("@ new item added");
        return "redirect:/item/" + newItem.getId();
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
}
