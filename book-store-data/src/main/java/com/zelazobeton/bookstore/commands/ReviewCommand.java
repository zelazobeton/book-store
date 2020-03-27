package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.BaseEntity;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewCommand {
    public Long id;
    public String text;
    public Integer stars;
    public User user;
    public Item item;

    public ReviewCommand() {}

    public ReviewCommand(Item item) {
        this.item = item;
    }
}
