package com.turkcell.identityService.api.controller;

import com.turkcell.identityService.business.abstracts.AuthService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.dtos.responses.LoggedInResponse;
import com.turkcell.identityService.business.dtos.responses.RefreshedTokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController{
    private final AuthService authService;

    @Value("${jwt.refresh.key}")
    private String refreshTokenKey;
    @Value("${jwt.refresh.days}")
    private int refreshTokenExpiryDays;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) {
        LoggedInResponse loggedInResponse = authService.login(loginRequest, getIpAddress(request));
        setCookie(refreshTokenKey, loggedInResponse.getRefreshToken(), refreshTokenExpiryDays * 24 * 60 * 60, response);
        return loggedInResponse.getAccessToken();
    }

    @PostMapping("/refresh")
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = String.valueOf(getCookie(request, refreshTokenKey));
        RefreshedTokenResponse refreshedTokenResponse = authService.refreshToken(refreshToken,getIpAddress(request));
        setCookie(refreshTokenKey, refreshedTokenResponse.getRefreshToken(), refreshTokenExpiryDays * 24 * 60 * 60, response);
        return refreshedTokenResponse.getAccessToken();
    }
}
