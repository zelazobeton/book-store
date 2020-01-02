package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.BaseEntity;
import com.zelazobeton.bookstore.model.Item;

import java.time.LocalDate;

public class ReviewCommand {
    public Long id;
    public String text;
    public Integer stars;
    public String author;
    public LocalDate date;
    public Item item;

    public ReviewCommand() {}

    public ReviewCommand(Item item) {
        this.item = item;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return text;
    }

    public void setDescription(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
