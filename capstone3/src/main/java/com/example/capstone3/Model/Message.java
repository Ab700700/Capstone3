package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "content should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String content;
    @NotEmpty(message = "status should not be empty")
    @Pattern( regexp = "new|seen",message = "message should ne new or seen")
    @Column(columnDefinition = "varchar(4) not null check(status='new' or status='seen')")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
