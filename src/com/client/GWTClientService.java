package com.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GWTClientService")
public interface GWTClientService extends RemoteService {
    String registerUser(String jsonMessage) throws IllegalArgumentException;
    String loadUser(String jsonMessage) throws IllegalArgumentException;
    String updateUser(String jsonMessage) throws IllegalArgumentException;
    void deleteUser(String username) throws IllegalArgumentException;
}
