package com.example.openschoolstarter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller just for showing how logging works.
 *
 */
@RestController
public class DemoController {
    @GetMapping("/get")
    public String get() {
        return "HelloMoto";
    }

    @PostMapping("/post")
    public String post(@RequestBody String body) {
        return body;
    }
}
