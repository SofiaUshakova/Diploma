package com.example.diploma.Controller;

import com.example.diploma.DataTransferObject.Request.AuthenticationRequest;
import com.example.diploma.DataTransferObject.Response.AuthenticationResponse;
import com.example.diploma.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authenticationService.logout(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
