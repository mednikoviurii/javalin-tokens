package com.codesityou.javalintokens.services;

import java.util.Optional;

import com.codesityou.javalintokens.entities.AuthRequest;
import com.codesityou.javalintokens.entities.AuthResponse;
import com.codesityou.javalintokens.entities.User;
import com.codesityou.javalintokens.managers.TokenManager;
import com.codesityou.javalintokens.repositories.UserRepository;
import io.javalin.http.ForbiddenResponse;

public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private TokenManager manager;

    public UserServiceImpl(UserRepository repository, TokenManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    @Override
    public AuthResponse signup(AuthRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        User user = repository.signup(email, password);
        String userId = user.getUserId();
        String token = manager.issueToken(userId);
        AuthResponse response = new AuthResponse(userId, token);
        return response;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        Optional<User> result = repository.findByEmail(email);
        if (result.isPresent()){
            User user = result.get();
            String passwordInDatabase = user.getPassword();
            if (password.equalsIgnoreCase(passwordInDatabase)) {
                String userId = user.getUserId();
                String token = manager.issueToken(userId);
                AuthResponse response = new AuthResponse(userId, token);
                return response;
            } else {
                throw new ForbiddenResponse();
            }
        } else {
            throw new ForbiddenResponse();
        }
    }

    @Override
    public boolean authorize(String token, String userId) {
        boolean result = manager.authorize(token, userId);
        return result;
    }


    
}