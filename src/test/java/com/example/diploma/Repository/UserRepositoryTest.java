//package com.example.diploma.Repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static com.example.diploma.Testdata.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//class UserRepositoryTest {
//
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        userRepository.save(USER_1);
//    }
//
//    @Test
//    void findByUsername() {
//        assertEquals(USER_1, userRepository.findByUsername(USERNAME_1));
//    }
//
//    @Test
//    void notFindByUsername() {
//        assertNull(userRepository.findByUsername(USERNAME_2));
//    }
//}
