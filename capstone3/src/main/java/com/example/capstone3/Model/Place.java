package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @Column(columnDefinition = "varchar(50) not null unique")
    private String companyName;
    @Column(columnDefinition = "varchar(30) not null")
    private String category;
    @Column(columnDefinition = "varchar(200) not null")
    private String description;
    @Column(columnDefinition = "int not null ")
    private Integer capacity;
    @ManyToOne
    @JoinColumn(name="business_id",referencedColumnName = "id")
    @JsonIgnore
    private Business business;
    @OneToMany( cascade = CascadeType.ALL,mappedBy = "place")
    private Set<Contest> contests;

    @ManyToOne
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    @JsonIgnore
    private Event event;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "placeorder")
    private Set<Orders> orders;
}
