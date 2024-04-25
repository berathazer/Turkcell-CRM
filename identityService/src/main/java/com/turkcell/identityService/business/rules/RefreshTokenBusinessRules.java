package com.turkcell.identityService.business.rules;

import com.turkcell.identityService.business.messages.RefreshTokenMessages;
import com.turkcell.identityService.core.business.abstracts.MessageService;
import com.turkcell.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.identityService.entitites.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RefreshTokenBusinessRules {
    private MessageService messageService;
    public void refreshTokenShouldBeExist(Optional<RefreshToken> refreshToken){
        if (refreshToken.isEmpty()){
            throw new BusinessException(messageService.getMessage(RefreshTokenMessages.REFRESH_TOKEN_NOT_FOUND));
        }
    }
    public void refreshTokenShouldNotBeExpired(RefreshToken refreshToken){
        if (refreshToken.getExpirationDate().isBefore(LocalDateTime.now())){
            throw new BusinessException(messageService.getMessage(RefreshTokenMessages.REFRESH_TOKEN_EXPIRED));
        }
    }
    public void refreshTokenShouldNotdBeRevoked(RefreshToken refreshToken){
        if (refreshToken.getRevokedDate() !=null){
            throw new BusinessException(messageService.getMessage(RefreshTokenMessages.REFRESH_TOKEN_REVOKED));
        }
    }
}