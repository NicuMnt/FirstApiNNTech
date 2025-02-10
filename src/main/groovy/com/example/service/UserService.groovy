package com.example.service

import com.example.dto.UserDTO
import com.example.model.User
import com.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException

@Service
class UserService {

    @Autowired
    UserRepository userRepository

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder()

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

    // ✅ Register a new user (with password hashing)
    UserDTO registerUser(User user) {
        if (userRepository.findByEmail(user.email) != null) {
            throw new RuntimeException("Email already registered!")
        }

        // Hash password before saving
        user.password = passwordEncoder.encode(user.password)

        User savedUser = userRepository.save(user)
        return new UserDTO(savedUser.id, savedUser.name, savedUser.email) // ✅ No password
    }


    UserDTO saveUser(UserDTO userDTO) {
        // Convert DTO to Entity
        User user = new User()
        user.name = userDTO.name
        user.email = userDTO.email
        user.password = passwordEncoder.encode(userDTO.password) // Encode password!

        // ✅ Save to DB
        User savedUser = userRepository.save(user)

        // ✅ Return DTO (to avoid exposing password)
        return new UserDTO(savedUser.id, savedUser.name, savedUser.email)
    }



    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID " + id + " not found.");
        }
        userRepository.deleteById(id);
    }
}
