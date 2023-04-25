package com.rogex.TodoAppBackend.auth;

import com.rogex.TodoAppBackend.Entity.Role;
import com.rogex.TodoAppBackend.Entity.Users;
import com.rogex.TodoAppBackend.Repository.UserRepository;
import com.rogex.TodoAppBackend.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public authenticationResponse register(registerRequest request) {
        var user = Users.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return authenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public authenticationResponse authenticate(authenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return authenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
