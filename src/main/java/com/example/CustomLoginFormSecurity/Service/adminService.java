package com.example.CustomLoginFormSecurity.Service;

import com.example.CustomLoginFormSecurity.Model.Admin;
import com.example.CustomLoginFormSecurity.Repository.adminRepository;
import com.example.CustomLoginFormSecurity.Security.AdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class adminService implements UserDetailsService {
    private adminRepository operations;

    @Autowired
    public void setOperations(adminRepository operations) {
        this.operations = operations;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = Optional.ofNullable(operations.findByUsername(username));
        if (adminOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new AdminDetails(adminOptional.get());
    }
}
