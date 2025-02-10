package com.example.dto

class UserDTO {
    Long id
    String name
    String email

    UserDTO(Long id, String name, String email) {
        this.id = id
        this.name = name
        this.email = email
    }
}