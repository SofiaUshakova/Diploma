package com.example.diploma.Service;

import com.example.diploma.DataTransferObject.Request.AuthenticationRequest;
import com.example.diploma.DataTransferObject.Response.AuthenticationResponse;
import com.example.diploma.Repository.AuthenticationRepository;
import com.example.diploma.SecurityJWT.Token;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {
    private AuthenticationRepository authenticationRepository;
    private Token token;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        final String username = authenticationRequest.getLogin();
        final String password = authenticationRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userService.loadUserByUsername(username);
        final String autToken = token.generateToken(userDetails);
        authenticationRepository.putTokenAndName(autToken, username);
        log.info("User {} authentication. JWT: {}", username, autToken);
        return new AuthenticationResponse(autToken);
    }

    public void logout(String token) {
        final String autToken = token.substring(7);
        final String username = authenticationRepository.getUsernameByToken(autToken);
        log.info("User {} logout. JWT is disabled.", username);
        authenticationRepository.removeTokenAndUsernameByToken(autToken);
    }

}
