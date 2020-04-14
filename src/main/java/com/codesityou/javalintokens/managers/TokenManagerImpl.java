package com.codesityou.javalintokens.managers;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.commons.lang3.RandomStringUtils;

public class TokenManagerImpl implements TokenManager {

    private ECKey key;
    private String keyId;

    public TokenManagerImpl() throws Exception{

        keyId = RandomStringUtils.randomAlphanumeric(50);
        key = new ECKeyGenerator(Curve.P_256).keyID(keyId).generate();

    }

    @Override
    public String issueToken(String userId) {
        try {
            JWSSigner signer = new ECDSASigner(key);
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject(userId).build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.ES256).keyID(keyId).build(), claimsSet);
            signedJWT.sign(signer);
            String token = signedJWT.serialize();
            return token;
        } catch (Exception ex){
            throw new RuntimeException();
        }
    }

    @Override
    public boolean authorize(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            ECKey publicKey = key.toPublicJWK();
            JWSVerifier verifier = new ECDSAVerifier(publicKey);
            boolean result = signedJWT.verify(verifier);
            return result;
        } catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

}