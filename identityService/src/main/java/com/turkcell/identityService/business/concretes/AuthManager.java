package com.turkcell.identityService.business.concretes;

import com.turkcell.identityService.business.abstracts.AuthService;
import com.turkcell.identityService.business.abstracts.RefreshTokenService;
import com.turkcell.identityService.business.abstracts.UserService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.dtos.responses.LoggedInResponse;
import com.turkcell.identityService.business.dtos.responses.RefreshedTokenResponse;
import com.turkcell.identityService.business.messages.AuthMessages;
import com.turkcell.identityService.core.business.abstracts.MessageService;
import com.turkcell.identityService.core.services.JwtService;
import com.turkcell.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.identityService.entitites.RefreshToken;
import com.turkcell.identityService.entitites.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final MessageService messageService;

    @Override
    public LoggedInResponse login(LoginRequest loginRequest, String ipAddress) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new BusinessException(messageService.getMessage(AuthMessages.LOGIN_FAILED));
        }
        User user = userService.findByUsername(loginRequest.getEmail());
        refreshTokenService.revokeOldTokens(user, ipAddress);
        RefreshToken refreshToken = refreshTokenService.create(user);
        String accessToken = generateJwt(user);
        return new LoggedInResponse(accessToken, refreshToken.getToken());
    }

    @Override
    public RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress) {
        RefreshToken token = refreshTokenService.verify(refreshToken);
        RefreshToken newToken = refreshTokenService.rotate(token, ipAddress);
        refreshTokenService.revokeOldTokens(token.getUser(), ipAddress);
        String accessToken = generateJwt(token.getUser());
        return new RefreshedTokenResponse(accessToken, newToken.getToken());
    }

    private String generateJwt(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        return jwtService.generateToken(user.getEmail(), claims);
    }
}
