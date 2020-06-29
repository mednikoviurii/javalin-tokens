package com.codesityou.javalintokens.services;


import com.codesityou.javalintokens.entities.AuthRequest;
import com.codesityou.javalintokens.entities.AuthResponse;

public interface UserService {

    AuthResponse signup (AuthRequest request);

    AuthResponse login (AuthRequest request);

    boolean authorize (String token, String userId);
}