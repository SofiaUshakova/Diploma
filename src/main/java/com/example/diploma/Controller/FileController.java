package com.example.diploma.Controller;

import com.example.diploma.DataTransferObject.Request.FileNameEditRequest;
import com.example.diploma.DataTransferObject.Response.FileResponse;
import com.example.diploma.Model.User;
import com.example.diploma.Repository.AuthenticationRepository;
import com.example.diploma.Service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.diploma.Repository.StorageFileRepository;
import com.example.diploma.Repository.UserRepository;
import java.util.stream.Collectors;

import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class FileController {
    private FileService fileService;
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    public User getUserByToken(String token) {
        if (token.startsWith("Bearer ")) {
            final String tokenWithoutBearer = token.split(" ")[1];
            final String username = authenticationRepository.getUsernameByToken(tokenWithoutBearer);
            return userRepository.findByUsername(username);
        }
        return null;
    }
    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename, MultipartFile file) {
        final User user = getUserByToken(token);
        fileService.uploadFile(user, filename, file);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename) {
        final User user = getUserByToken(token);
        fileService.deleteFile(user, filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename) {
        final User user = getUserByToken(token);
        byte[] file = fileService.downloadFile(user, filename);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }
    @PutMapping(value = "/file")
    public ResponseEntity<?> editFileName(@RequestHeader("auth-token") String token, @RequestParam("filename") String filename, @RequestBody FileNameEditRequest fileNameEditRequest) {
        final User user = getUserByToken(token);
        fileService.editFileName(user, filename, fileNameEditRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<FileResponse> getAllFiles(@RequestHeader("auth-token") String token, @RequestParam("limit") Integer limit) {
        final User user = getUserByToken(token);
        return fileService.getAllFiles(user, limit).stream()
                .map(o -> new FileResponse(o.getFileName(), o.getSize()))
                .collect(Collectors.toList());
    }
}
