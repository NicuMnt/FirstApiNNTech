package com.example.controller

class Documentation {

    String Documentation =
            "What is it?\n" +
            "A Controller in Spring Boot is responsible for handling incoming HTTP requests and returning responses. " +
                    "It acts as a bridge between the user and the backend logic.\n" +
            "\n" +
            "📌 Your files:\n" +
            "\n" +
            "HelloController → Might handle generic greetings (e.g., /hello route).\n" +
            "HomeController → Likely the main entry point (e.g., /home route).\n" +
            "UserController → Manages user-related requests (e.g., /users route)."


    String fixes = " Summary of Fixes\n" +
            "Issue\tCause\tFix\n" +
            "POST: \"Cannot cast UserDTO to User\"\tTrying to save UserDTO instead of User\tConvert UserDTO to User before saving\n" +
            "DELETE: \"User deleted successfully\" even if ID doesn’t exist\tNo check for existing user before deletion\tCheck existsById(id) before deleting"

}
