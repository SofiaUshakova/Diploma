package com.example.diploma.Service;

import com.example.diploma.DataTransferObject.Request.FileNameEditRequest;
import com.example.diploma.DataTransferObject.Response.FileResponse;
import com.example.diploma.Exeption.InputException;
import com.example.diploma.Exeption.UnauthorizedException;
import com.example.diploma.Model.StorageFile;
import com.example.diploma.Model.User;
import com.example.diploma.Repository.AuthenticationRepository;
import com.example.diploma.Repository.StorageFileRepository;
import com.example.diploma.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FileService {
    private final AuthenticationRepository authenticationRepository;
    private final StorageFileRepository storageFileRepository;
    private final UserRepository userRepository;

    private User getUserByToken(String token) {
        if (token.startsWith("Bearer ")) {
            final String tokenWithoutBearer = token.split(" ")[1];
            final String username = authenticationRepository.getUsernameByToken(tokenWithoutBearer);
            return userRepository.findByUsername(username);
        }
        return null;
    }
    public boolean uploadFile(String authToken, String filename, MultipartFile file) {
        final User user = getUserByToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new UnauthorizedException("Upload file: Unauthorized");
        }

        try {
            storageFileRepository.save(new StorageFile(user, filename,  file.getSize(), LocalDateTime.now(), file.getBytes()));
            log.info("Success upload file. User {}", user.getUsername());
            return true;
        } catch (IOException e) {
            log.error("Upload file: Input data exception");
            throw new InputException("Upload file: Input data exception");
        }
    }

    @Transactional
    public void deleteFile(String authToken, String filename) {
        final User user = getUserByToken(authToken);
        if (user == null) {
            log.error("Delete file: Unauthorized");
            throw new UnauthorizedException("Delete file: Unauthorized");
        }

        storageFileRepository.deleteFileByUser(filename, user);

        final StorageFile tryingToGetDeletedFile = storageFileRepository.findByFileAndUser(filename,user);
        if (tryingToGetDeletedFile != null) {
            log.error("Delete file: Input data exception");
            throw new InputException("Delete file: Input data exception");
        }
        log.info("Success delete file. User {}", user.getUsername());
    }
    public byte[] downloadFile(String token, String filename) {
        final User user = getUserByToken(token);
        if (user == null) {
            log.error("Download file: Unauthorized");
            throw new UnauthorizedException("Download file: Unauthorized");
        }

        final StorageFile file = storageFileRepository.findByFileAndUser(filename, user);
        if (file == null) {
            log.error("Download file: Input data exception");
            throw new InputException("Download file: Input exception");
        }
        log.info("Success download file. User {}", user.getUsername());
        return file.getFileContent();
    }
    @Transactional
    public void editFileName(String token, String filename, FileNameEditRequest fileNameEditRequest) {
        final User user = getUserByToken(token);
        if (user == null) {
            log.error("Edit file name: Unauthorized");
            throw new UnauthorizedException("Edit file name: Unauthorized");
        }

        storageFileRepository.editFileByUser(user, filename, fileNameEditRequest.getFileName());

        final StorageFile fileWithOldName = storageFileRepository.findByFileAndUser(filename, user);
        if (fileWithOldName != null) {
            log.error("Edit file name: Input data exception");
            throw new InputException("Edit file name: Input exception");
        }
        log.info("Success edit file name. User {}", user.getUsername());
    }
    public List<FileResponse> getAllFiles(String token, Integer limit) {
        final User user = getUserByToken(token);
        if (user == null) {
            log.error("Get all files: Unauthorized");
            throw new UnauthorizedException("Get all files: Unauthorized");
        }
        log.info("Success get all files. User {}", user.getUsername());
        return storageFileRepository.findAllByUser(user).stream()
                .map(o -> new FileResponse(o.getFileName(), o.getSize()))
                .collect(Collectors.toList());
    }


}
