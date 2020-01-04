package com.zelazobeton.bookstore.commands;

import com.zelazobeton.bookstore.model.Item;

import java.time.LocalDate;

public class ItemCommand {
    public Long id;
    public String text;
    public Integer stars;
    public String author;
    public LocalDate date;
    public Item item;

    public ItemCommand() {}
}
