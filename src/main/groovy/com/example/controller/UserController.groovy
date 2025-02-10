package com.example.controller

import com.example.dto.UserDTO
import com.example.model.User
import com.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private UserService userService

    @GetMapping
    ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{email}")
    ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {  // Fix here
        User user = userService.getUserByEmail(email)
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User userDetails) { // Fix here
        try {
            User updatedUser = userService.updateUser(id, userDetails)
            return ResponseEntity.ok(updatedUser)
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<User> partialUpdateUser(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        try {
            User updatedUser = userService.partialUpdateUser(id, updates)
            return ResponseEntity.ok(updatedUser)
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    // ✅ Register a new user
    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user)
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser)
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage())
        }
    }

    @PostMapping("/users")
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO) // ✅ Fix: return DTO
        return ResponseEntity.ok(savedUser) // ✅ Return DTO, not entity
    }


}
