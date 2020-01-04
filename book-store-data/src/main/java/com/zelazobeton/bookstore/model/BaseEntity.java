package com.zelazobeton.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
