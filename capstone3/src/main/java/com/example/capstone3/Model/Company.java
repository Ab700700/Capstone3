package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "company name should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String company_name;
    @NotNull(message = "password should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;
    @NotNull(message = "email should not be null")
    @Email(message = "should be valid email")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotNull(message = "phone number should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String phone_number;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "active|notactive",message = "Status should be active or notactive")
    @Column(columnDefinition = "varchar(10) not null check(status = 'active' or status ='notactive')")
    private String status;
    @NotNull(message = "permission should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String permission;
    @NotNull(message = "commercial register number should be  not be null")
    @Size(min = 10,max = 10,message = "commercial register number must be 10")
    @Pattern(regexp = "70.[0-9]+", message = "commercial register number start with 70 and contains only numbers")
    @Column(columnDefinition = "varchar(255) not null")
    private String commercial_register_num;
    @NotNull(message = "profit should not be null")
    @Column(columnDefinition = "double not null")
    private Double profit;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private Set<Event>events;

    @ManyToMany
    @JsonIgnore
    private Set<User> users;
}
