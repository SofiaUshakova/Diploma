package com.example.diploma.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StorageFile {
    @ManyToOne
    private User user;
    @Id
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private Long size;
    @Column(nullable = false)
    private LocalDateTime localDateTime;
    @Column(nullable = false)
    private byte[] fileContent;


}
