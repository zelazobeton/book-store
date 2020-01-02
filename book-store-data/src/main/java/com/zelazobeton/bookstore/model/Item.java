package com.zelazobeton.bookstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "items")
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

    public Item() {}
    public Item(ItemBuilder itemBuilder){
        this.name = itemBuilder.name;
        this.descriptionShort = itemBuilder.descriptionShort;
        this.descriptionFull = itemBuilder.descriptionFull;
        this.price = itemBuilder.price;
        this.categories = itemBuilder.categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public static class ItemBuilder{
        private String name = "name empty";
        private String descriptionShort = "description empty";
        private String descriptionFull = "description empty";
        private double price = 0.00d;
        private Set<Category> categories = new HashSet<>();

        public ItemBuilder() {}
        public ItemBuilder name(String name){this.name = name; return this;}
        public ItemBuilder descriptionShort(String description){this.descriptionShort = description; return this;}
        public ItemBuilder descriptionFull(String description){this.descriptionFull = description; return this;}
        public ItemBuilder price(double price){this.price = price; return this;}
        public ItemBuilder addCategory(Category category){this.categories.add(category); return this;}
        public Item build(){
            return new Item(this);
        }
    }
}
