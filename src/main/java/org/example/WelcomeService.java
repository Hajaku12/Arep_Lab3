package org.example;

@RestController
public class WelcomeService {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, World!";
    }
}