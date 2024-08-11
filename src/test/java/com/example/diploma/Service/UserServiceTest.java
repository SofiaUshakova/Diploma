package com.example.diploma.Service;

import com.example.diploma.Exeption.UnauthorizedException;
import com.example.diploma.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.example.diploma.Testdata.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(userRepository.findByUsername(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void loadUserByUsername() {
        assertEquals(USER_1, userService.loadUserByUsername(USERNAME_1));
    }

    @Test
    void loadUserByUsernameUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> userService.loadUserByUsername(USERNAME_2));
    }
}
