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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Company name should not be empty")
    @Pattern(regexp = "[a-zA-Z]+",message = "Company name should be letters only")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String companyName;
    @NotNull(message = "Capacity should not be empty")
    @Positive(message = "Capacity should be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer capacity;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 20,message = "Description should be at least 20 characters")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "accepted|denied|pending")
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
}
