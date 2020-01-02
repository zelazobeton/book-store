package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.ReviewCommand;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Review extends BaseEntity {
    @Lob
    private String text;
    private Integer stars;
    private String author;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    public Review() {}
    public Review(ReviewCommand command) {
        this.id = command.id;
        this.text = command.text;
        this.stars = 0;
        this.author = "Anon";
        this.date = command.date;
        this.item = command.item;
    }


    public void setReviewText(String text) {
        this.text = text;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
