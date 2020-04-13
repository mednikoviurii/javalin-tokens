package com.codesityou.javalintokens.services;

import com.codesityou.javalintokens.entities.AuthRequest;
import com.codesityou.javalintokens.entities.AuthResponse;
import com.codesityou.javalintokens.entities.User;
import com.codesityou.javalintokens.managers.TokenManager;
import com.codesityou.javalintokens.repositories.UserRepository;

import org.easymock.EasyMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;


public class UserServiceImplTest {

    private UserRepository repository;
    private TokenManager manager;
    private UserServiceImpl service;

    @BeforeClass
    public void setup(){
        repository = EasyMock.mock(UserRepository.class);
        manager = EasyMock.mock(TokenManager.class);
        service = new UserServiceImpl(repository, manager);
    }

    @Test
    public void signupTest(){
        final AuthRequest request = new AuthRequest("user@email.com", "secret");
        final String token ="token";
        final User user = new User("userId", "user@email.com", "secret");
        EasyMock.expect(repository.signup("user@email.com", "secret")).andReturn(user);
        EasyMock.expect(manager.issueToken(user.getUserId())).andReturn(token);
        EasyMock.replay(repository, manager);
        AuthResponse result = service.signup(request);
        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getToken()).isEqualTo(token);
    }

    @Test
    public void loginTest(){
        final AuthRequest request = new AuthRequest("user@email.com", "secret");
        final String token ="token";
        final User user = new User("userId", "user@email.com", "secret");
        EasyMock.expect(repository.findByEmail("user@email.com")).andReturn(Optional.of(user));
        EasyMock.expect(manager.issueToken(user.getUserId())).andReturn(token);
        EasyMock.replay(repository, manager);
        AuthResponse result = service.login(request);
        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getToken()).isEqualTo(token);
    }

    @Test
    public void loginDeniedTest(){
        final AuthRequest request = new AuthRequest("user@email.com", "secret");
        EasyMock.expect(repository.findByEmail("user@email.com")).andReturn(Optional.empty());
        EasyMock.replay(repository);
        AuthResponse result = service.login(request);
        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isFalse();
    }

    @Test
    public void authorizeTest(){
        String token = "token";
        EasyMock.expect(manager.authorize(token)).andReturn(true);
        EasyMock.replay(manager);
        boolean result = service.authorize(token);
        assertThat(result).isTrue();
    }

    @Test
    public void authorizeDeniedTest(){
        String token = "token";
        EasyMock.expect(manager.authorize(token)).andReturn(false);
        EasyMock.replay(manager);
        boolean result = service.authorize(token);
        assertThat(result).isFalse();
    }
}