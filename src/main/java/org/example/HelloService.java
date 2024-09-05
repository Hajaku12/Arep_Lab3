package org.example;
@RestController
public class HelloService {

    @GetMapping("/hello1")
    public static String hello() {
        return "Hello, World!";
    }
}
