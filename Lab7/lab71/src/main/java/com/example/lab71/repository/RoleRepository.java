package com.example.lab71.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab71.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @SuppressWarnings("null")
    Optional<Role> findById(String id);
}
