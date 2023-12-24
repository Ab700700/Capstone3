package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class PassDTO {


    private Integer id;
    @NotNull(message = "event id should not be null")
    private Integer event_id;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "active|notactive",message = "Status should be active or notactive")
    private String status;
    @NotNull(message = "start date should not be null")
    private LocalDateTime start_date;
    @NotNull(message = "end date should not be null")
    private LocalDateTime end_date;
    @NotNull(message = "price should not be null")
    @PositiveOrZero(message = "price should be positive or zero")
    private Double price;
}
