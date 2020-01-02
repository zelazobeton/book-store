package com.zelazobeton.bookstore.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "items")
public class Item extends BaseEntity {
    private String name;
    @Lob
    private String description;
    private double price;
    @ManyToMany
    @JoinTable(name = "item_category",
               joinColumns = @JoinColumn(name = "item_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Item() {}
    public Item(ItemBuilder itemBuilder){
        this.name = itemBuilder.name;
        this.description = itemBuilder.description;
        this.price = itemBuilder.price;
        this.categories = itemBuilder.categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public static class ItemBuilder{
        private String name = "name empty";
        private String description = "description empty";
        private double price = 0.00d;
        private Set<Category> categories = new HashSet<>();

        public ItemBuilder() {}
        public ItemBuilder name(String name){this.name = name; return this;}
        public ItemBuilder description(String description){this.description = description; return this;}
        public ItemBuilder price(double price){this.price = price; return this;}
        public ItemBuilder addCategory(Category category){this.categories.add(category); return this;}
        public Item build(){
            return new Item(this);
        }
    }
}
