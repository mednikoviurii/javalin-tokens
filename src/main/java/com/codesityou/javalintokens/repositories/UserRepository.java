package com.codesityou.javalintokens.repositories;

import java.util.Optional;

import com.codesityou.javalintokens.entities.User;

public interface UserRepository {

    User signup (String email, String password);

    Optional<User> findByEmail (String email);
    
}