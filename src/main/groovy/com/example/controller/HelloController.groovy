package com.example.controller
//bind mannualy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// bind all of them
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
class HelloController {

    @GetMapping("/hello")
    String sayHello() {
        return "Hello from Spring Boot with Groovy!"
    }

    @PostMapping("/hello")
    public ResponseEntity<String> postHello(@RequestBody Map<String, String> requestBody) {
        String message = requestBody.get("message");
        return ResponseEntity.ok("Received: " + message);
    }
}


