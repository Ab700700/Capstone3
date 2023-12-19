package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EventDTO {

    private Integer company_id;
    @NotNull(message = "event name should not be null")
    private String event_name;
    @NotNull(message = "start date should not be null")
    private Date start_date;
    @NotNull(message = "end date should not be null")
    private Date end_date;
    @NotNull(message = "tickets should not be null")
    @Positive(message = "tickets should be positive")
    private Integer tickets;
}
