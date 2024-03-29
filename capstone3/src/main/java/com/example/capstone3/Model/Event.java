package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class  Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(255) not null")
    private String event_name;
    @Column(columnDefinition = "date not null")
    private LocalDateTime start_date;
    @Column(columnDefinition = "date not null")
    private LocalDateTime end_date;
    @Column(columnDefinition = "int not null check(tickets>0)")
    private Integer tickets;
    @Column(columnDefinition = "varchar(20) not null")
    private String category;
    @Column(columnDefinition = "varchar(20) not null")
    private String city;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "event")
    private Set<Pass> passes;

    @ManyToOne
    @JoinColumn(name = "company_id",referencedColumnName = "id")
    @JsonIgnore
    private Company company;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "event")
    private Set<Orders>orders;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "event")
    private Set<Place>places;
}
