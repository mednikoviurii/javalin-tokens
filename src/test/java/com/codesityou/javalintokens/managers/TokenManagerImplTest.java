package com.codesityou.javalintokens.managers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class TokenManagerImplTest {

    private TokenManagerImpl manager;

    @BeforeClass
    public void setup() throws Exception{
        manager = new TokenManagerImpl();
        assertThat(manager).isNotNull();
    }

    @Test
    public void issueTokenTest(){
        final String userId = "user";
        String token = manager.issueToken(userId);
        assertThat(token).isNotEmpty();
    }

    @Test
    public void authorizeTest(){
        final String userId = "user";
        String token = manager.issueToken(userId);
        assertThat(token).isNotEmpty();
        boolean result = manager.authorize(token);
        assertThat(result).isTrue();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void authorizeFailTest(){
        final String token = "some-token";
        manager.authorize(token);
    }
}