package com.example.CustomLoginFormSecurity.Repository;

import com.example.CustomLoginFormSecurity.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
