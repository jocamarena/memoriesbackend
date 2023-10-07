package com.example.memoriesbackend.records;

public record UserRecord(Long id, String firstName, String middleName, String lastName, String email, String role, boolean active) {
}
