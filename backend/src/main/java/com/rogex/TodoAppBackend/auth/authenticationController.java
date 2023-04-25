package com.rogex.TodoAppBackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class authenticationController {

    private final authenticationService service;

    @PostMapping("/register")
    public ResponseEntity<authenticationResponse> register(
            @RequestBody registerRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<authenticationResponse> authenticate(
            @RequestBody authenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
