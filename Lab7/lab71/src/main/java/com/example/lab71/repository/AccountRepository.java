package com.example.lab71.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab71.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username); 
}
