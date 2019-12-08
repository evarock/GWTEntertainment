package com.client.data;

public class ClientInfo {
    private User currentUser;
    private static ClientInfo instance;

    public static ClientInfo getInstance() {
        if (instance == null) {
            instance = new ClientInfo();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
