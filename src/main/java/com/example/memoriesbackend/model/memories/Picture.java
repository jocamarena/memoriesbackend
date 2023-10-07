package com.example.memoriesbackend.model.memories;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDate dateTaken;
    private String description;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
}
