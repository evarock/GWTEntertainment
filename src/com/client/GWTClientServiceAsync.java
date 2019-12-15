package com.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTClientServiceAsync {
    void registerUser(String jsonMessage, AsyncCallback<String> callback) throws IllegalArgumentException;
    void loadUser(String jsonMessage, AsyncCallback<String> callback) throws IllegalArgumentException;
    void updateUser(String jsonMessage, AsyncCallback<String> callback) throws IllegalArgumentException;
    void deleteUser(String username, AsyncCallback<Void> callback) throws IllegalArgumentException;
}
