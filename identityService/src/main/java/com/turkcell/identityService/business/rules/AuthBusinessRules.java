package com.turkcell.identityService.business.rules;

import com.turkcell.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.identityService.dataAccess.UserRepository;
import com.turkcell.identityService.entitites.User;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthBusinessRules {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean authenticateUser(String email, String password) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return true;
            } else {
                throw new BusinessException("Wrong user name or password. Please try again");
            }
        } else {
            throw new BusinessException("Wrong user name or password. Please try again");
        }
    }
}