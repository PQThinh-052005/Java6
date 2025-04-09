package com.example.lab71.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab71.entity.Authority;
import com.example.lab71.repository.AccountRepository;
import com.example.lab71.repository.AuthorityRepository;
import com.example.lab71.repository.RoleRepository;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    public Authority addAuthority(String username, String roleId) {
        Authority auth = new Authority();
        auth.setAccount(accountRepository.findByUsername(username));
        auth.setRole(roleRepository.findById(roleId).orElseThrow());
        return authorityRepository.save(auth);
    }

    public Authority deleteAuthority(String username, String roleId) {
        Authority auth = authorityRepository.findByAccountUsernameAndRoleId(username, roleId).orElseThrow();
        authorityRepository.delete(auth);
        return auth;
    }
    
}
