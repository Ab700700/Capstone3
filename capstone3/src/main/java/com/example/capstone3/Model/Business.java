package com.example.capstone3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 10,max = 10, message = "Phone number should be 10 numbers")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(50) not null unique ")
    private String email;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "active|notactive",message = "Status should be active or notactive")
    @Column(columnDefinition = "varchar(10) not null check(status = 'active' or status='notactive')")
    private String  status;
    @NotEmpty(message = "Company name should not be empty")
    @Pattern(regexp = "[a-zA-Z]+",message = "Company name should be letters only")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String companyName;
    @NotEmpty(message = "Commercial register number should not be empty")
    @Size(min = 10,max = 10,message = "Commercial register number should be 10 numbers")
    @Pattern(regexp = "70.[0-9]+",message = "Commercial register number should start with 70 and contains only numbers")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String commercialRegisterNum;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 20, message = "Description should be at least 20 characters")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "business")
    private Set<Place> places;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "business")
    private Set<Order> orders;
}
