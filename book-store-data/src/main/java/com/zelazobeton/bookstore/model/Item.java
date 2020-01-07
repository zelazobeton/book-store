package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.ItemCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Item extends BaseEntity {
    private String name;
    @Lob
    private String descriptionShort;
    @Lob
    private String descriptionFull;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Review> reviews = new HashSet<>();
    private double price;
    @ManyToMany
    @JoinTable(name = "item_category",
               joinColumns = @JoinColumn(name = "item_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
    @Lob
    private Byte[] image;

    public Item() {}
    public Item(ItemCommand command){
        this.name = command.getName();
        this.descriptionShort = command.getDescriptionShort();
        this.descriptionFull = command.getDescriptionFull();
        this.price = command.getPrice();
        for(Category cat : command.getCategories()){
            addCategory(cat);
        }
        this.image = command.getImage();
    }

    public void addCategory(Category category){
        categories.add(category);
        while(!category.isBasicCategory()){
            category = category.getSupercategory();
            categories.add(category);
        }
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
