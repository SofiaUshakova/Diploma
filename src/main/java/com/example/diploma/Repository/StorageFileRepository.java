package com.example.diploma.Repository;

import com.example.diploma.Model.StorageFile;
import com.example.diploma.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageFileRepository extends JpaRepository {
    StorageFile findByFileAndUser(String fileName, User user);

    void deleteFileByUser(String fileName, User user);

    //Обновляем базу
    @Modifying
    @Query("update StorageFile f set f.fileName = :newName where f.fileName = :filename and f.user = :user")
    void editFileByUser(@Param("user") User user, @Param("filename") String filename, @Param("newName") String newName);

    List<StorageFile> findAllByUser(User user);

}
