package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdersDTO {

    private Integer businessid;
    private Integer placeid;
    private Integer eventid;
    @NotEmpty(message = "Company name should not be empty")
    @Pattern(regexp = "[a-zA-Z]+",message = "Company name should be letters only")
    private String companyName;
    @NotNull(message = "Capacity should not be empty")
    @Positive(message = "Capacity should be a positive number")
    private Integer capacity;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 20,message = "Description should be at least 20 characters")
    private String description;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "accepted|denied|pending")
    private String status;
}
