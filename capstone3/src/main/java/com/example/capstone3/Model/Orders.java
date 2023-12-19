package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(50) not null unique")
    private String companyName;
    @Column(columnDefinition = "int not null")
    private Integer capacity;
    @Column(columnDefinition = "varchar(255) not null")
    private String description;
    @Column(columnDefinition = "varchar(9) not null check(status='accepted' or status ='denied' or status ='pending')")
    private String status;
    @ManyToOne
    @JoinColumn(name="business_id",referencedColumnName = "id")
    @JsonIgnore
    private Business business;

    @ManyToOne
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name = "placeorderid",referencedColumnName = "id")
    @JsonIgnore
    private Place placeorder;
}
