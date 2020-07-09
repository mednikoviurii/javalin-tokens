package com.codesityou.javalintokens.entities;

import me.geso.tinyvalidator.constraints.Email;
import me.geso.tinyvalidator.constraints.Size;

public class AuthRequest {
    
    @Email
    private String email;

    @Size(min = 8)
    private String password;

    public AuthRequest(){

    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}