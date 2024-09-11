package com.example.CustomLoginFormSecurity.Security;

import com.example.CustomLoginFormSecurity.Model.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AdminDetails implements UserDetails {
    private final Admin admin;

    public AdminDetails(Admin admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Здесь можно вернуть роли администратора
        return null; // Замените на вашу логику, если у вас есть роли
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Логика проверки срока действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Логика проверки блокировки аккаунта
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Логика проверки срока действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return true; // Логика проверки активности аккаунта
    }

    public Admin getAdmin() {
        return admin;
    }
}
