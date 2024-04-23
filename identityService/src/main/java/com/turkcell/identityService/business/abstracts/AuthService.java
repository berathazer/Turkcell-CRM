package com.turkcell.identityService.business.abstracts;

import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.dtos.responses.LoggedInResponse;
import com.turkcell.identityService.business.dtos.responses.RefreshedTokenResponse;


public interface AuthService {
    LoggedInResponse login(LoginRequest loginRequest, String ipAddress);

    RefreshedTokenResponse refreshToken(String refreshToken, String ipAddress);
}

