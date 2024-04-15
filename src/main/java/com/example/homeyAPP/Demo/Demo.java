package com.example.homeyAPP.Demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class Demo {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from secured endpoint");
    }
}
