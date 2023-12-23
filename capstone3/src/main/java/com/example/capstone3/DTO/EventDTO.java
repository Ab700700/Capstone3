package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class EventDTO {

    private Integer company_id;
    @NotNull(message = "event name should not be null")
    private String event_name;
    @NotNull(message = "start date should not be null")
    private LocalDateTime start_date;
    @NotNull(message = "end date should not be null")
    private LocalDateTime end_date;
    @NotNull(message = "tickets should not be null")
    @Positive(message = "tickets should be positive")
    private Integer tickets;
    @NotNull(message = "category should not be null")
    private String category;
    @NotNull(message = "city should not be null")
    private String city;
}
