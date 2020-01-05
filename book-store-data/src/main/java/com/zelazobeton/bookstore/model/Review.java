package com.zelazobeton.bookstore.model;

import com.zelazobeton.bookstore.commands.ReviewCommand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Review extends BaseEntity {
    @Lob
    private String text;
    private Integer stars;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User author;
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
        this.author = command.author;
        this.date = command.date;
        this.item = command.item;
    }

    @Override
    public String toString() {
        return text;
    }
}
