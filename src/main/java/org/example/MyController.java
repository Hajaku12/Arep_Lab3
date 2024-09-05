package org.example;

@RestController
public class MyController {

    @PostMapping("/hellopost")
    public String handlePost(@RequestParam("name") String name) {
        return "Hello, " + name;
    }

}