package com.example.memoriesbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private boolean active;
    @Transient
    private String name = firstName + " " + middleName + " " + lastName;
}
