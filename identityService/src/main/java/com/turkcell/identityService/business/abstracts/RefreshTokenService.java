package com.turkcell.identityService.business.abstracts;

import com.turkcell.identityService.entitites.RefreshToken;
import com.turkcell.identityService.entitites.User;

public interface RefreshTokenService {
    RefreshToken create(User user);
    RefreshToken verify(String token);
    RefreshToken rotate(RefreshToken token,String ipAddress);
    void revokeOldTokens(User user, String ipAddress);
}

