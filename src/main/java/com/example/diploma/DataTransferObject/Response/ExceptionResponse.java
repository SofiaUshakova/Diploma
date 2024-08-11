package com.example.diploma.DataTransferObject.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private int id;
}
