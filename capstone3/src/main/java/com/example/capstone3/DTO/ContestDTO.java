package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContestDTO {

    private Integer placeid;
    @NotNull(message = "competitors should not be empty")
    @Positive(message = "competitors should be a positive number")
    private Integer competitors;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 20,message = "Description should be at least 20 characters")
    private String description;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "public|private",message = "Status should be public or private")
    private String status;
}
