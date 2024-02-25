package com.example.memoriesbackend.model.memories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    @OneToOne
    private Picture picture;
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
}
