package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceDTO {

    private Integer id ;
    @NotNull(message = "event id should not be empty")
    private Integer event_id;
    private String companyName;
    @NotEmpty(message = "Category should not be empty")
    private String category;
    @NotNull(message = "Capacity should not be empty")
    @PositiveOrZero(message = "Capacity should be positive number or zero")
    private Integer capacity;
    @NotEmpty(message = "Description should not be empty")
    private String description;
}
