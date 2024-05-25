package com.turkcell.identityService.business.concretes;

import com.turkcell.crm.core.util.JwtService;
import com.turkcell.identityService.business.abstracts.AuthService;
import com.turkcell.identityService.business.abstracts.UserService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.dtos.requests.RegisterRequest;
import com.turkcell.identityService.entitites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public String login(LoginRequest loginRequest) {
        // TODO: Handle Exception.
        // TODO: E-posta da şifre de yanlış olursa olsun, mesaj ve yanıt kalıbı birebir aynı olmalı.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if (!authentication.isAuthenticated())
            throw new RuntimeException("E-posta ya da şifre yanlış");

        UserDetails user = userService.loadUserByUsername(loginRequest.getEmail());
        // TODO: Refactor
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user
                .getAuthorities()
                .stream()
                .map((role) -> role.getAuthority())
                .toList();
        claims.put("roles", roles);
        return jwtService.generateToken(loginRequest.getEmail(),roles);
    }

    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        // Hashing
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Hassas bilgiler veritabanına "PLAIN TEXT" olarak yazılmaz.
        userService.add(user);

    }
}
