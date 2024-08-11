package com.example.diploma.Controller;

import com.example.diploma.DataTransferObject.Request.FileNameEditRequest;
import com.example.diploma.DataTransferObject.Response.FileResponse;
import com.example.diploma.Service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class FileController {
    private FileService fileService;
    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename, MultipartFile file) {
        fileService.uploadFile(token, filename, file);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename) {
        fileService.deleteFile(token, filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename) {
        byte[] file = fileService.downloadFile(token, filename);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }
    @PutMapping(value = "/file")
    public ResponseEntity<?> editFileName(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename, @RequestBody FileNameEditRequest fileNameEditRequest) {
        fileService.editFileName(token, filename, fileNameEditRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<FileResponse> getAllFiles(@RequestHeader("auth-token") String token, @RequestParam("limit") Integer limit) {
        return fileService.getAllFiles(token, limit);
    }

}
