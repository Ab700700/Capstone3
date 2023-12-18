package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @NotEmpty(message = "Company name should not be empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String companyName;
    @NotEmpty(message = "Category should not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String category;
    @NotEmpty(message = "Description should not be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;
}
