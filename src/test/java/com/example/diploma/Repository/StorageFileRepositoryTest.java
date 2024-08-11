//package com.example.diploma.Repository;
//
//import com.example.diploma.Model.StorageFile;
//import com.example.diploma.Model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.diploma.Testdata.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class StorageFileRepositoryTest {
//    private StorageFileRepository storageFileRepository;
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        storageFileRepository.deleteAll();
//        userRepository.save(USER_1);
//        storageFileRepository.save(STORAGE_FILE_1);
//        storageFileRepository.save(FOR_RENAME_STORAGE_FILE);
//    }
//
//    @Test
//    void deleteFileByUser() {
//        Optional<StorageFile> beforeDelete = storageFileRepository.findById(FILENAME_1);
//        assertTrue(beforeDelete.isPresent());
//        storageFileRepository.deleteFileByUser(FILENAME_1, USER_1);
//        Optional<StorageFile> afterDelete = storageFileRepository.findById(FILENAME_1);
//        assertFalse(afterDelete.isPresent());
//    }
//
//    @Test
//    void findByFileAndUser() {
//        assertEquals(STORAGE_FILE_1, storageFileRepository.findByFileAndUser(FILENAME_1, USER_1));
//    }
//
//    @Test
//    void editFileByUser() {
//        Optional<StorageFile> beforeEditName = storageFileRepository.findById(FOR_RENAME_FILENAME);
//        assertTrue(beforeEditName.isPresent());
//        assertEquals(FOR_RENAME_FILENAME, beforeEditName.get().getFileName());
//        storageFileRepository.editFileByUser(USER_1, FOR_RENAME_FILENAME, NEW_FILENAME);
//        Optional<StorageFile> afterEditName = storageFileRepository.findById(NEW_FILENAME);
//        assertTrue(afterEditName.isPresent());
//        assertEquals(NEW_FILENAME, afterEditName.get().getFileName());
//    }
//
//    @Test
//    void findAllByUser() {
//        assertEquals(List.of(STORAGE_FILE_1, FOR_RENAME_STORAGE_FILE), storageFileRepository.findAllByUser(USER_1));
//    }
//}
