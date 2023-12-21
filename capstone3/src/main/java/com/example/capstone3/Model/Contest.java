package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    private Integer competitors;
    @Column(columnDefinition = "varchar(255) not null")
    private String description;
    @Column(columnDefinition = "varchar(9) not null check(status='public' or status='private')")
    private String status;
    @Column(columnDefinition = "date not null")
    private LocalDate startdate;
    @Column(columnDefinition = "date not null")
    private LocalDate enddate;
       @ManyToOne
   @JoinColumn(name = "place_id",referencedColumnName = "id")
   @JsonIgnore
    private Place place;
    @ManyToMany
    @JsonIgnore
    private Set<User> users;

}
