package com.example.lab71.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab71.dto.AuthorityDTO;
import com.example.lab71.entity.Authority;
import com.example.lab71.service.AuthorityService;


@RestController
@RequestMapping("/api/authorities")
@CrossOrigin(origins = "*")
public class AuthorityAController {

    @Autowired
    AuthorityService authorityService;

    

    @GetMapping
    public List<Authority> getAll() {
        return authorityService.getAllAuthorities();
    }
    
    @PostMapping
    public Authority grantAuthority(@RequestBody AuthorityDTO dto) {
        return authorityService.addAuthority(dto.getUsername(), dto.getRoleId());
    }

    @DeleteMapping
    public void revokeAuthority(@RequestBody AuthorityDTO dto) {
        authorityService.deleteAuthority(dto.getUsername(), dto.getRoleId());
    }
}


