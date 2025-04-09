package com.example.lab71.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab71.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    
    Optional<Authority> findByAccountUsernameAndRoleId(String username, String roleId);
}
