package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "competitors should not be empty")
    @Positive(message = "competitors should be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer competitors;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 20,message = "Description should be at least 20 characters")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;
    @NotEmpty(message = "Status should not be empty")
    @Pattern(regexp = "public|private",message = "Status should be public or private")
    @Column(columnDefinition = "varchar(9) not null check(status='public' or status='private')")
    private String status;

}
