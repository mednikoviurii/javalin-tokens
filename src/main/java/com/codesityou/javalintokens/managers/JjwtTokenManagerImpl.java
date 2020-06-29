package com.codesityou.javalintokens.managers;

import io.javalin.http.ForbiddenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JjwtTokenManagerImpl implements TokenManager {

    private final Key key;

    public JjwtTokenManagerImpl() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    @Override
    public String issueToken(String userId) {
        String token = Jwts.builder().setSubject(userId).signWith(key).compact();
        return token;
    }

    @Override
    public boolean authorize(String token, String userId) {
        try {
            String subject = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
            return subject.equalsIgnoreCase(userId);
        } catch (Exception ex){
            throw new ForbiddenResponse();
        }
    }
}
