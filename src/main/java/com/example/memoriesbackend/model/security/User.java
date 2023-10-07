package com.example.memoriesbackend.model.security;

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
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private boolean active;
    @Transient
    private String nameFirstMiddleLast;
    public String getNameFirstMiddleLast() {
        return String.format("s% s% s%", this.getFirstName(), this.getMiddleName(), this.getLastName());
    }
}
