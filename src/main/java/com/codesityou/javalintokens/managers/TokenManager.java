package com.codesityou.javalintokens.managers;

public interface TokenManager {

    String issueToken (String userId);

    boolean authorize (String token);
    
}