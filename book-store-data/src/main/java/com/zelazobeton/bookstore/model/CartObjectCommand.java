package com.zelazobeton.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class CartObjectCommand extends BaseEntity{
    private Integer amount;
    private Long itemId;

    public CartObjectCommand(Long itemId) {
        this.amount = 1;
        this.itemId = itemId;
    }
}
