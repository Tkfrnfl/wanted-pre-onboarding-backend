package com.example.wanted.controller;

import com.example.wanted.dto.SignUpForm;
import com.example.wanted.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody  SignUpForm signUpForm) throws Exception {

        return ResponseEntity.ok(userService.signUpService(signUpForm));
    }

    @GetMapping ("/signIn")
    public ResponseEntity<String> signIn(@RequestBody  SignUpForm signUpForm) throws Exception {

        return ResponseEntity.ok(userService.signInService(signUpForm));
    }
}
