package com.example.lab71.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    private String username;

    private String password;
    private String fullname;
    private String email;
    private String photo;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Authority> authorities;
}

