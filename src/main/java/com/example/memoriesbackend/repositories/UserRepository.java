package com.example.memoriesbackend.repositories;

import com.example.memoriesbackend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public List<User> findAllByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);
    public List<User> findAllByFirstNameAndLastName(String firstName, String lastName);
    public List<User> findAllByLastName(String lastName);
    public List<User> findAllByActiveAndRole(boolean active, String role);
    public List<User> findAll();
}
