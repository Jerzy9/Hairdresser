package com.hairdresser.booking.api;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HairServRestController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @PostMapping
    public void getAllServs(@RequestBody String query) {

    }
}