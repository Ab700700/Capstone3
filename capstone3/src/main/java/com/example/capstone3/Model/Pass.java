package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "active|notactive",message = "Status should be active or notactive")
    @Column(columnDefinition = "varchar(10) not null check(status = 'active' or status ='notactive')")
    private String status;
    @NotNull(message = "start date should not be null")
    @Column(columnDefinition = "date not null")
    private LocalDateTime start_date;
    @NotNull(message = "end date should not be null")
    @Column(columnDefinition = "date not null")
    private LocalDateTime end_date;
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
