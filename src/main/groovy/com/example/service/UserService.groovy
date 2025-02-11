package com.example.service

import com.example.dto.UserDTO
import com.example.model.User
import com.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@Service
class UserService {

    @Autowired
    UserRepository userRepository

    // ✅ Return list of users as UserDTO (hides passwords)
    List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll()
        println("Users from DB: " + users)  // 🔍 Debug: Print users to console

        return users.collect { user ->
            new UserDTO(user.id, user.name, user.email) // ✅ No password included
        }
    }

    // ✅ Fetch a user by email and return as UserDTO
    UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
        if (user != null) {
            return new UserDTO(user.id, user.name, user.email) // ✅ No password included
        }
        return null
    }

    // ✅ Save new user (returns UserDTO)
    UserDTO saveUser(User user) {
        User savedUser = userRepository.save(user)
        return new UserDTO(savedUser.id, savedUser.name, savedUser.email) // ✅ No password
    }

    String registerUser(User user) {
        if (userRepository.findByEmail(user.email) != null) {
            // ✅ Return proper HTTP 409 Conflict instead of 500 error
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered!")
        }

        // Save user
        userRepository.save(user)

        return "Your account has been successfully registered!"
    }

    // ✅ Save UserDTO (without password encoding)
    UserDTO saveUser(UserDTO userDTO) {
        User user = new User()
        user.name = userDTO.name
        user.email = userDTO.email
        user.password = userDTO.password

        User savedUser = userRepository.save(user)
        return new UserDTO(savedUser.id, savedUser.name, savedUser.email)
    }

    // ✅ Delete user
    void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID " + id + " not found.")
        }
        userRepository.deleteById(id)
    }
}
