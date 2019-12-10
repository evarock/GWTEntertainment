package com.client.data;

import com.client.widgets.HeaderTable;

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
        HeaderTable.getInstance().updateLayout();
    }
}
