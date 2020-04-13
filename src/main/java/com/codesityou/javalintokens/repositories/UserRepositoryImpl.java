package com.codesityou.javalintokens.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import com.codesityou.javalintokens.entities.User;

import org.apache.commons.lang3.RandomStringUtils;

public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public User signup(String email, String password) {
        try (Connection connection = dataSource.getConnection()){

            String userId = RandomStringUtils.randomAlphanumeric(25);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (user_id, email, password) VALUES (?,?,?);");

            statement.setString(1, userId);
            statement.setString(2, email);
            statement.setString(3, password);

            return new User(userId, email, password);

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection()){

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email=?;");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String password = resultSet.getString("password");
                String userId = resultSet.getString("user_id");

                User user = new User(userId, email, password);
                return Optional.of(user);
                
            } else {
                return Optional.empty();
            }

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    
}