package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class ContestDTO {

    private Integer placeid;
    @NotNull(message = "competitors should not be empty")
    @PositiveOrZero(message = "competitors should be a positive number")
    private Integer competitors;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 20,message = "Description should be at least 20 characters")
    private String description;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "public|private",message = "Status should be public or private")
    private String status;
    @NotNull(message = "Start date should not be empty")
    @FutureOrPresent(message = "Start date should be present or in the future")
    private LocalDateTime startdate;
    @NotNull(message = "End date should not be empty")
    @FutureOrPresent(message = "end date should be present or in the future")
    private LocalDateTime enddate;
}
