package com.example.memoriesbackend.api;

import com.example.memoriesbackend.dto.UserDto;
import com.example.memoriesbackend.dto.UserRequestDto;
import com.example.memoriesbackend.model.User;
import com.example.memoriesbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
        userService.getUserByEmail(userRequestDto.getEmail()).ifPresent(user -> {
            throw new RuntimeException("User already exists");
        });
        User user = userService.saveUser(User.builder()
                .name(userRequestDto.getFirstName() + " " + userRequestDto.getMiddleName() + " " + userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .role(userRequestDto.getRole())
                .build());
        return ResponseEntity.ok(UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build());
    }
    @GetMapping("/id")
    public ResponseEntity<UserDto> getUserById(@RequestParam String id) {
        User user = userService.getUserById(Long.parseLong(id));
        return ResponseEntity.ok(UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build());
    }
    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(UserDto.builder()
                .id(user.getId())
                .name(user.getName())
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
