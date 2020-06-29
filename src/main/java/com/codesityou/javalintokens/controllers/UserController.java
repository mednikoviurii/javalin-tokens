package com.codesityou.javalintokens.controllers;

import com.codesityou.javalintokens.entities.AuthRequest;
import com.codesityou.javalintokens.entities.AuthResponse;
import com.codesityou.javalintokens.services.UserService;

import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public void login (Context context){
        AuthRequest request = context.bodyAsClass(AuthRequest.class);
        AuthResponse result = service.login(request);
        context.json(result);
    }

    public void signup (Context context){
        AuthRequest request = context.bodyAsClass(AuthRequest.class);
        AuthResponse result = service.signup(request);
        context.json(result);
    }

    public void authorize (Context context){
        String token = context.header("Authorization");
        String userId = context.header("X-User-ID");
        if (token == null){
            throw new ForbiddenResponse();
        }
        boolean result = service.authorize(token, userId);
        if (!result){
            throw new ForbiddenResponse();
        }
    }
}