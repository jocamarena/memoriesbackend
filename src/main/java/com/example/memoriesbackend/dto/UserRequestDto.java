package com.example.memoriesbackend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String role;
}
