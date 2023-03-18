package com.example.jwt_accesstoken_refreshtoken.Rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
@CrossOrigin(origins = "http://localhost:5173/")
@Slf4j
public class Testing {
    @GetMapping("/test")
    public String get(){
        log.info("HERE WE ARE!!");
        return "HELLO WORLD!!";
    }
}
