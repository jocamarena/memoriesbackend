package com.example.memoriesbackend.api;


import com.example.memoriesbackend.model.security.User;
import com.example.memoriesbackend.records.ExceptionRecord;
import com.example.memoriesbackend.records.UpdateUserRecord;
import com.example.memoriesbackend.records.UserRecord;
import com.example.memoriesbackend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRestControllerV2 {
    private final UserService userService;
    public UserRestControllerV2(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserRecord>> getUsers(){
        List<UserRecord> userRecords = new ArrayList<>();
        userService.getAllUsers().forEach(user -> {
            userRecords.add(new UserRecord(user.getId(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getEmail(), user.getRole(), user.isActive()));
        });

        return ResponseEntity.ok(userRecords);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable Long id){
        UserRecord userRecord = userService.getUserById(id).map(user -> {
            return new UserRecord(user.getId(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getEmail(), user.getRole(), user.isActive());
        }).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(userRecord);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userService.delete(id);
        return ResponseEntity.ok(String.format("User id: %d deleted successfully", id));
    }
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserRecord userRecord) throws RuntimeException{
        userService.getUserByEmail(userRecord.email()).ifPresent(user -> {
            throw new RuntimeException("User already exists");
        });
        User user = User.builder()
                .active(userRecord.active())
                .email(userRecord.email())
                .firstName(userRecord.firstName())
                .middleName(userRecord.middleName())
                .lastName(userRecord.lastName())
                .role(userRecord.role()).build();
        this.userService.saveUser(user);
        return ResponseEntity.created(URI.create("/api/v2/users/" + user.getId())).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserRecord> updateUser(@PathVariable Long id, @RequestBody UpdateUserRecord userRecord) throws RuntimeException{
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(userRecord.firstName() != null){
            user.setFirstName(userRecord.firstName());
        }
        if(userRecord.middleName() != null){
            user.setMiddleName(userRecord.middleName());
        }
        if(userRecord.lastName() != null){
            user.setLastName(userRecord.lastName());
        }
        if(userRecord.email() != null){
            user.setEmail(userRecord.email());
        }
        User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(new UserRecord(updatedUser.getId(), updatedUser.getFirstName(), updatedUser.getMiddleName(), updatedUser.getLastName(), updatedUser.getEmail(), updatedUser.getRole(), updatedUser.isActive()));
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionRecord> handleException(RuntimeException e, HttpServletRequest request){
        ExceptionRecord exceptionRecord = new ExceptionRecord(e.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(exceptionRecord);
    }
}
