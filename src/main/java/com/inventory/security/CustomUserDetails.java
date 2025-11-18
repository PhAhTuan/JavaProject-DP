package com.inventory.security;

import com.inventory.entity.SystemAccount;
import com.inventory.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    
    private final SystemAccount account;
    private final UserRole userRole;
    
    public CustomUserDetails(SystemAccount account, UserRole userRole) {
        this.account = account;
        this.userRole = userRole;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = "ROLE_" + userRole.name();
        return List.of(new SimpleGrantedAuthority(roleName));
    }
    
    @Override
    public String getPassword() {
        return account.getPassword();
    }
    
    @Override
    public String getUsername() {
        return account.getEmail();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE.equals(account.getIsActive());
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(account.getIsActive());
    }
    
    public UserRole getUserRole() {
        return userRole;
    }
}