package com.example.demo.bean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String fullname;

    @NotNull
    @Min(0)
    @Max(10)
    private Double marks;

    @NotNull
    private Boolean gender;

    @NotEmpty
    private String country;
}