package com.example.memoriesbackend.services;

import com.example.memoriesbackend.model.security.User;
import com.example.memoriesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<User> getAllUsersByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName) {
        return userRepository.findAllByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName);
    }
    public List<User> getAllUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }
    public List<User> getAllUsersByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }
    public List<User> getAllUsersByActiveAndRole(boolean active, String role) {
        return userRepository.findAllByActiveAndRole(active, role);
    }
    public Integer countAllUsers() {
        return userRepository.findAll().size();
    }
}
