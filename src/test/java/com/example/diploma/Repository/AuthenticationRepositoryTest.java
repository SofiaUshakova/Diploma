//package com.example.diploma.Repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import static com.example.diploma.Testdata.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class AuthenticationRepositoryTest {
//    private AuthenticationRepository authenticationRepository;
//    private final Map<String, String> testAut = new ConcurrentHashMap<>();
//
//    @BeforeEach
//    void setUp() {
//        authenticationRepository = new AuthenticationRepository();
//        authenticationRepository.putTokenAndName(TOKEN_1, USERNAME_1);
//        testAut.clear();
//        testAut.put(TOKEN_1, USERNAME_1);
//    }
//
//    @Test
//    void putTokenAndName() {
//        String beforePut = authenticationRepository.getUsernameByToken(TOKEN_2);
//        assertNull(beforePut);
//        authenticationRepository.putTokenAndName(TOKEN_2, USERNAME_2);
//        String afterPut = authenticationRepository.getUsernameByToken(TOKEN_2);
//        assertEquals(USERNAME_2, afterPut);
//    }
//
//    @Test
//    void removeTokenAndUsernameByToken() {
//        String beforeRemove = authenticationRepository.getUsernameByToken(TOKEN_1);
//        assertNotNull(beforeRemove);
//        authenticationRepository.removeTokenAndUsernameByToken(TOKEN_1);
//        String afterRemove = authenticationRepository.getUsernameByToken(TOKEN_1);
//        assertNull(afterRemove);
//    }
//
//    @Test
//    void getUsernameByToken() {
//        assertEquals(testAut.get(TOKEN_1), authenticationRepository.getUsernameByToken(TOKEN_1));
//    }
//}
