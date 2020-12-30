package com.itvillage.afridigaming.dto.response;

/**
 * Created by Rasel on 5/26/2019.
 */

public class LoginResponse {

    private String token;


    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
