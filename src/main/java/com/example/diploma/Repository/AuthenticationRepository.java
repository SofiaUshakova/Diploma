package com.example.diploma.Repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthenticationRepository {
    private final Map<String, String> tokenAndName = new ConcurrentHashMap<>();

    public void putTokenAndName(String token, String username) {
        tokenAndName.put(token, username);
    }

    public String getUsernameByToken(String token) {
        return tokenAndName.get(token);
    }

    public void removeTokenAndUsernameByToken(String token) {
        tokenAndName.remove(token);
    }
}
