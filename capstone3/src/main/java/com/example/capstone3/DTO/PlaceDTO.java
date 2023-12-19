package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceDTO {

    private Integer id ;
    @NotEmpty(message = "event id should not be empty")
    private Integer event_id;
    @NotEmpty(message = "Company name should not be empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String companyName;
    @NotEmpty(message = "Category should not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String category;
    @NotEmpty(message = "Description should not be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;
}
