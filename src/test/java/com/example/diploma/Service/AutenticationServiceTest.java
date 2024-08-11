package com.example.diploma.Service;

import com.example.diploma.Repository.AuthenticationRepository;
import com.example.diploma.SecurityJWT.Token;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;

import static com.example.diploma.Testdata.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AutenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationRepository authenticationRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Token jwtTokenUtil;

    @Mock
    private UserService userService;

    @Test
    void login() {
        Mockito.when(userService.loadUserByUsername(USERNAME_1)).thenReturn(USER_1);
        Mockito.when(jwtTokenUtil.generateToken(USER_1)).thenReturn(TOKEN_1);
        assertEquals(AUTHENTICATION_RS, authenticationService.login(AUTHENTICATION_RQ));
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(USERNAME_PASSWORD_AUTHENTICATION_TOKEN);
        Mockito.verify(authenticationRepository, Mockito.times(1)).putTokenAndName(TOKEN_1, USERNAME_1);
    }

    @Test
    void logout() {
        Mockito.when(authenticationRepository.getUsernameByToken(BEARER_TOKEN_SUBSTRING_7)).thenReturn(USERNAME_1);
        authenticationService.logout(BEARER_TOKEN);
        Mockito.verify(authenticationRepository, Mockito.times(1)).getUsernameByToken(BEARER_TOKEN_SUBSTRING_7);
        Mockito.verify(authenticationRepository, Mockito.times(1)).removeTokenAndUsernameByToken(BEARER_TOKEN_SUBSTRING_7);
    }
}
