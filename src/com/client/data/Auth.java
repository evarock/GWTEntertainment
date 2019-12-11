package com.client.data;

import com.google.gwt.json.client.*;

public class Auth {
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private String username;
    private String password;

    public Auth() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String createJson(Auth auth) {
        if (auth == null || auth.getUsername() == null || auth.getUsername().isEmpty() ||
            auth.getPassword() == null || auth.getPassword().isEmpty()) {
            return null;
        }
        JSONObject json = new JSONObject();
        try {
            json.put(USERNAME_KEY, new JSONString(auth.getUsername()));
            json.put(PASSWORD_KEY, new JSONString(auth.getPassword()));
        } catch (Exception e) {
            return null;
        }
        return json.toString();
    }
}
