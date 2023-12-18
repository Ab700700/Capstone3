package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name="business_id",referencedColumnName = "id")
    @JsonIgnore
    private Business business;
    @OneToMany( cascade = CascadeType.ALL,mappedBy = "place")
    private Set<Contest> contests;
}
