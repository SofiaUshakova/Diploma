package com.example.diploma.DataTransferObject.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationRequest {
    @NotBlank(message = "login must not be null")
    private String login;

    @NotBlank(message = "password must not be null")
    private String password;
}
