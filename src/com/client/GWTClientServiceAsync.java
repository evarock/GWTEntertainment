package com.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTClientServiceAsync {
    void registerUser(String jsonMessage, AsyncCallback<String> callback) throws IllegalArgumentException;
    void loadUser(String username, AsyncCallback<String> callback) throws IllegalArgumentException;
    void updateUser(String jsonMessage, AsyncCallback<String> callback) throws IllegalArgumentException;
    void deleteUser(String username, AsyncCallback<Void> callback) throws IllegalArgumentException;;

//    void loadUserWithAuth(String jsonMessage, AsyncCallback<String> callback) throws IllegalArgumentException;
    void loadUserWithAuth(String jsonMessage, AsyncCallback<Void> callback) throws IllegalArgumentException;
}
