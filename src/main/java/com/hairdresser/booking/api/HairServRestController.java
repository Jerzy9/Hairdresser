package com.hairdresser.booking.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HairServRestController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }
}
