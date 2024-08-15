package com.example.diploma.Service;

import com.example.diploma.Exeption.InputException;
import com.example.diploma.Exeption.UnauthorizedException;
import com.example.diploma.Repository.AuthenticationRepository;
import com.example.diploma.Repository.StorageFileRepository;
import com.example.diploma.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.example.diploma.Testdata.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Mock
    private StorageFileRepository storageFileRepository;

    @Mock
    private AuthenticationRepository authenticationRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(authenticationRepository.getUsernameByToken(BEARER_TOKEN_SPLIT)).thenReturn(USERNAME_1);
        Mockito.when(userRepository.findByUsername(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void uploadFile() {
        assertTrue(fileService.uploadFile(USER_1, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void uploadFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> fileService.uploadFile(USER_1, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void deleteFile() {
        fileService.deleteFile(USER_1, FILENAME_1);
        Mockito.verify(storageFileRepository, Mockito.times(1)).deleteFileByUser(FILENAME_1, USER_1);
    }

    @Test
    void deleteFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> fileService.deleteFile(USER_1, FILENAME_1));
    }

    @Test
    void deleteFileInputException() {
        Mockito.when(storageFileRepository.findByFileAndUser(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(InputException.class, () -> fileService.deleteFile(USER_1, FILENAME_1));
    }

    @Test
    void downloadFile() {
        Mockito.when(storageFileRepository.findByFileAndUser(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertEquals(FILE_CONTENT_1, fileService.downloadFile(USER_1, FILENAME_1));
    }

    @Test
    void downloadFileUnauthorized() {
        Mockito.when(storageFileRepository.findByFileAndUser(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(UnauthorizedException.class, () -> fileService.downloadFile(USER_1, FILENAME_1));
    }

    @Test
    void downloadFileInputDataException() {
        Mockito.when(storageFileRepository.findByFileAndUser(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(InputException.class, () -> fileService.downloadFile(USER_2, FILENAME_2));
    }

    @Test
    void editFileName() {
        fileService.editFileName(USER_1, FILENAME_1, EDIT_FILE_NAME_RQ);
        Mockito.verify(storageFileRepository, Mockito.times(1)).editFileByUser(USER_1, FILENAME_1, NEW_FILENAME);
    }

    @Test
    void editFileNameUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> fileService.editFileName(USER_1, FILENAME_1, EDIT_FILE_NAME_RQ));
    }

    @Test
    void editFileNameInputDataException() {
        Mockito.when(storageFileRepository.findByFileAndUser(FILENAME_1, USER_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(InputException.class, () -> fileService.deleteFile(USER_1, FILENAME_1));
    }

    @Test
    void getAllFiles() {
        Mockito.when(storageFileRepository.findAllByUser(USER_1)).thenReturn(STORAGE_FILE_LIST);
        assertEquals(FILE_RS_LIST, fileService.getAllFiles(USER_1, LIMIT));
    }

    @Test
    void getAllFilesUnauthorized() {
        Mockito.when(storageFileRepository.findAllByUser(USER_1)).thenReturn(STORAGE_FILE_LIST);
        assertThrows(UnauthorizedException.class, () -> fileService.getAllFiles(USER_1, LIMIT));
    }
}
