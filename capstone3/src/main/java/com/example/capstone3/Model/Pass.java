package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "event name should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String status;
    @NotNull(message = "start date should not be null")
    @Column(columnDefinition = "date not null")
    private Date start_date;
    @NotNull(message = "end date should not be null")
    @Column(columnDefinition = "date not null")
    private Date end_date;
    @NotNull(message = "price should not be null")
    @PositiveOrZero(message = "price should be positive or zero")
    @Column(columnDefinition = "int not null check(price>=0)")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    @JsonIgnore
    private Event event;
}