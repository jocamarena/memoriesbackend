package com.example.memoriesbackend.api;

import com.example.memoriesbackend.dto.UserDto;
import com.example.memoriesbackend.dto.UserRequestDto;
import com.example.memoriesbackend.model.security.User;
import com.example.memoriesbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserRestController.class);

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
        userService.getUserByEmail(userRequestDto.getEmail()).ifPresent(user -> {
            throw new RuntimeException("User already exists");
        });
        User user = userService.saveUser(User.builder()
                .firstName(userRequestDto.getFirstName())
                .middleName(userRequestDto.getMiddleName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .role(userRequestDto.getRole())
                .build());
        return ResponseEntity.ok(UserDto.builder()
                .id(user.getId())
                .name(user.getNameFirstMiddleLast())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build());
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        userService.getAllUsers().stream().forEach(user -> {
            users.add(UserDto.builder()
                    .id(user.getId())
                    .name(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .active(user.isActive())
                    .build());
        });
        return users;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        userService.delete(Long.parseLong(id));
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        logger.info("getUserById: " + id);
        User user = userService.getUserById(Long.parseLong(id)).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(UserDto.builder()
                .id(user.getId())
                .name(user.getNameFirstMiddleLast())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        logger.info("getUserByEmail: " + email);
        User user = userService.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(UserDto.builder()
                .id(user.getId())
                .name(user.getNameFirstMiddleLast())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAllUsers() {
        return ResponseEntity.ok(userService.countAllUsers());
    }
}
