package com.example.diploma.DataTransferObject.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {
    private String fileName;
    private Long size;
}
