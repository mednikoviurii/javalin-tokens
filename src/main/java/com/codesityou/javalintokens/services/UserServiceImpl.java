package com.codesityou.javalintokens.services;

import java.util.Optional;

import com.codesityou.javalintokens.entities.AuthRequest;
import com.codesityou.javalintokens.entities.AuthResponse;
import com.codesityou.javalintokens.entities.User;
import com.codesityou.javalintokens.managers.TokenManager;
import com.codesityou.javalintokens.repositories.UserRepository;

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
        AuthResponse response = new AuthResponse(userId, token, true);
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
                AuthResponse response = new AuthResponse(userId, token, true);
                return response;
            } else {
                AuthResponse response = AuthResponse.bad();
                return response;
            }
        } else {
            AuthResponse response = AuthResponse.bad();
            return response;
        }
    }

    @Override
    public boolean authorize(String token) {
        boolean result = manager.authorize(token);
        return result;
    }


    
}