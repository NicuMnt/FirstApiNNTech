package com.example.controller

import com.example.model.User
import com.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    UserService userService

    @GetMapping
    List<User> getUsers() {
        return userService.getAllUsers()
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        return userService.saveUser(user)
    }

    @GetMapping("/{email}")
    User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
    }
}