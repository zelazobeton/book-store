package com.zelazobeton.bookstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    public Category() {}
    public Category(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supercategory")
    private List<Category> subcategories = new ArrayList<>();
    @ManyToOne
    private Category supercategory = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void addSubcategory(Category subcategory) {
        subcategory.setSupercategory(this);
        this.subcategories.add(subcategory);
    }

    @Override
    public String toString() {
        return name.replace(' ', '-');
    }

    public Category getSupercategory() {
        return supercategory;
    }

    public void setSupercategory(Category supercategory) {
        this.supercategory = supercategory;
    }

    public boolean isBasicCategory(){
        return supercategory == null;
    }
}
