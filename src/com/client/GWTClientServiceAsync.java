package com.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTClientServiceAsync {
    void loadUser(String username, AsyncCallback<String> callback) throws IllegalArgumentException;
}
