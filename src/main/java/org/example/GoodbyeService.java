package org.example;

@RestController
public class GoodbyeService {

    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye, World!";
    }
}