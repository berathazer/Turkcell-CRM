package com.turkcell.identityService.business.rules;

import com.turkcell.identityService.business.messages.AuthMessages;
import com.turkcell.identityService.core.business.abstracts.MessageService;
import com.turkcell.identityService.core.utilities.exceptions.types.BusinessException;

import com.turkcell.identityService.dataAccess.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/*
@Service
@AllArgsConstructor
public class AuthBusinessRules {
    private UserRepository userRepository;
    private MessageService messageService;

    public void checkIfAuthenticated() {
        if (!authentication.isAuthenticated()) {
            throw new BusinessException(messageService.getMessage(AuthMessages.LOGIN_FAILED));
        }
    }
}*/
