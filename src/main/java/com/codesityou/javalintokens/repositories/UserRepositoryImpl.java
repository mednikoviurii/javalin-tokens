package com.codesityou.javalintokens.repositories;

import com.codesityou.javalintokens.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class UserRepositoryImpl implements UserRepository{

    private List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public User signup(String email, String password) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, email, password);
        users.add(user);
        return user;

    }

    @Override
    public Optional<User> findByEmail(String email) {
        Predicate<User> query = user -> user.getEmail().equalsIgnoreCase(email);
        return users.stream().filter(query).findFirst();
    }
}
