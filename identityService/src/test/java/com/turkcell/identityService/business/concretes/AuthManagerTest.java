package com.turkcell.identityService.business.concretes;

import com.turkcell.crm.core.util.JwtService;
import com.turkcell.identityService.business.abstracts.UserService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.dtos.requests.RegisterRequest;
import com.turkcell.identityService.business.rules.AuthBusinessRules;
import com.turkcell.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.identityService.entitites.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AuthManagerTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthBusinessRules authBusinessRules;
    @InjectMocks
    private AuthManager authManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testLogin() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        doNothing().when(authBusinessRules).checkIfAuthenticated(loginRequest);

        UserDetails userDetails = mock(UserDetails.class);
        when(userService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("test@example.com");
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
        when(jwtService.generateToken(anyString(), anyList())).thenReturn("jwtToken");

        String result = authManager.login(loginRequest);
        assertEquals("jwtToken", result);

        verify(authBusinessRules, times(1)).checkIfAuthenticated(loginRequest);
        verify(userService, times(1)).loadUserByUsername("test@example.com");
        verify(jwtService, times(1)).generateToken("test@example.com", Collections.emptyList());
    }
    @Test
    void testLoginFailed() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        doThrow(new BusinessException("Login failed")).when(authBusinessRules).checkIfAuthenticated(loginRequest);

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authManager.login(loginRequest);
        });

        assertEquals("Login failed", exception.getMessage());

        verify(authBusinessRules, times(1)).checkIfAuthenticated(loginRequest);
        verify(userService, never()).loadUserByUsername(anyString());
        verify(jwtService, never()).generateToken(anyString(), anyList());
    }
    @Test
    void testRegister() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("password");

        User user = new User();
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        authManager.register(registerRequest);

        verify(userService, times(1)).add(any(User.class));
    }
}