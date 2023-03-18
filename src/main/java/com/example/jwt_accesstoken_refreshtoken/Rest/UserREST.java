package com.example.jwt_accesstoken_refreshtoken.Rest;

import com.example.jwt_accesstoken_refreshtoken.Repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:5173/")

public class UserREST {
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public ResponseEntity<?> getData(){
        var list=userRepository.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("#user.id == #id")
//    @SecurityRequirement(name = "bearerAuth")
//    public ResponseEntity<?> me(@AuthenticationPrincipal User user, @PathVariable String id) {
//        return ResponseEntity.ok(userRepository.findById(id));
//    }
}
