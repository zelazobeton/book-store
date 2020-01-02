package com.zelazobeton.bookstore.model;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
