package com.zelazobeton.bookstore.model;

import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends User implements UserDetails {

    public SecurityUser() {}
    public SecurityUser(User user) {
        super.setId(user.getId());
        super.setUsername(user.getUsername());
        super.setPassword(user.getPassword());
        super.setAuthorities(user.getAuthorities());
        super.setCart(user.getCart());
    }

    @Override
    public boolean isAccountNonExpired() {
        // e.g after user decision to delete account
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // e.g after series of invalid login attempts
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
