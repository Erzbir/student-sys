package com.erzbir.sys.entity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class User implements Serializable {
    private String username = "";
    private String password = "";

    public @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder username(String username) {
            user.setUsername(username);
            return this;
        }

        public Builder password(String password) {
            user.setPassword(password);
            return this;
        }

        public User build() {
            return user;
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User user)) {
            return false;
        }
        return this.username.equals(user.username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
