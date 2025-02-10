package com.example.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "users") // ✅ Correct table name
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name
    String email


    String password
}
