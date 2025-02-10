package com.example.service

import com.example.model.User
import com.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    UserRepository userRepository

    List<User> getAllUsers() {
        return userRepository.findAll()
    }

    User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
    }

    User saveUser(User user) {
        return userRepository.save(user)
    }
}
