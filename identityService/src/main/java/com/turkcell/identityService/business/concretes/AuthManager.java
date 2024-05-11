package com.turkcell.identityService.business.concretes;

import com.turkcell.identityService.business.abstracts.AuthService;
import com.turkcell.identityService.business.abstracts.UserService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.messages.AuthMessages;
import com.turkcell.identityService.core.business.abstracts.MessageService;
import com.turkcell.identityService.core.services.JwtService;
import com.turkcell.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.identityService.entitites.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final MessageService messageService;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(!authentication.isAuthenticated())
            throw new BusinessException(AuthMessages.LOGIN_FAILED);
        User user = userService.findByUsername(request.getEmail());
        String jwt = generateJwt(user);

        return jwt;
    }
    private String generateJwt(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        return jwtService.generateToken(user.getEmail(), claims);
    }
}
