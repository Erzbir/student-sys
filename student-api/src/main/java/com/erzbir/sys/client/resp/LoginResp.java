package com.erzbir.sys.client.resp;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public class LoginResp {
    private String token;
    private String username;

    public LoginResp(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
