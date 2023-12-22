package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "first name should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String firstname;
    @NotNull(message = "last name should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String lastname;
    @NotNull(message = "phone number should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String phone_number;
    @NotNull(message = "password should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;
    @NotNull(message = "email should not be null")
    @Email(message = "should be valid email")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @Pattern(regexp = "admin|visitor",message = "role should be admin or visitor")
    private String role;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    private Set<Pass>passes;

    @ManyToMany(mappedBy = "users")
    private Set<Contest> contests;
    @ManyToMany(mappedBy = "users")
    private Set<Company> companies;

}
