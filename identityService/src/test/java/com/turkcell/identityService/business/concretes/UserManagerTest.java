package com.turkcell.identityService.business.concretes;

import com.turkcell.identityService.dataAccess.UserRepository;
import com.turkcell.identityService.entitites.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManagerTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserManager userManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {

        User user = new User();

        userManager.add(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testLoadUserByUsernameSuccess() {

        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        UserDetails userDetails = userManager.loadUserByUsername(user.getEmail());

        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsernameNotFound() {

        String email = "nonexistent@example.com";

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        assertThrows(BadCredentialsException.class, () -> {
            userManager.loadUserByUsername(email);
        });
    }
}