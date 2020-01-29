package com.zelazobeton.bookstore.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Authorities extends BaseEntity implements GrantedAuthority {
//    private static final Long serialVersionUID = -81515641654165L;

    public Authorities(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public Authorities() {
    }

    private String authority;
    @ManyToOne
    private User user;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
