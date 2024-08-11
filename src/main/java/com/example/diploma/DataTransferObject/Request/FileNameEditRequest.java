package com.example.diploma.DataTransferObject.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class FileNameEditRequest {
    private String fileName;

    @JsonCreator
    public FileNameEditRequest(String fileName){
        this.fileName = fileName;
    }
}
