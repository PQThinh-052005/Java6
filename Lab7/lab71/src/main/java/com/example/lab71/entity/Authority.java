package com.example.lab71.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnoreProperties({"authorities","password","fullname","email","photo"})
    @JoinColumn(name = "Username")
    private Account account;

    @ManyToOne 
    @JsonIgnoreProperties({"authorities","name"})
    @JoinColumn(name = "Roleid")
    private Role role;
}
