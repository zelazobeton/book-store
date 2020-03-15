package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ItemCommand{
    private Long id;
    private String name = "Item name";
    private String descriptionShort = "Short item description";
    private String descriptionFull = "Full item description";
    private double price = 0.00d;
    private Set<Category> categories = new HashSet<>();
    private Byte[] image = null;
    private MultipartFile multipartImageFile = null;

    public ItemCommand() {}
    public ItemCommand(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.descriptionShort = item.getDescriptionShort();
        this.descriptionFull = item.getDescriptionFull();
        this.price = item.getPrice();
        this.categories = item.getCategories();
        this.image = item.getImage();
    }

    public ItemCommand name(String name){this.name = name; return this;}
    public ItemCommand descriptionShort(String description){this.descriptionShort = description; return this;}
    public ItemCommand descriptionFull(String description){this.descriptionFull = description; return this;}
    public ItemCommand price(double price){this.price = price; return this;}
    public ItemCommand addCategory(Category category){this.categories.add(category); return this;}
    public ItemCommand image(Byte[] image){this.image = image; return this;}
    public Item buildItem(){
        return new Item(this);
    }
}