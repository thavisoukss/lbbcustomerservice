package com.lbb.customer.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class HelloController {

    @GetMapping("/testtoken")
    public String testtoken(Authentication authentication) {

        return "testtoken " + authentication.getName();
    }

    @GetMapping("/test")
    public String test() {

        return  "hello customer servcie";
    }
}
