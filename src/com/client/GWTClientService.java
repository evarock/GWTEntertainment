package com.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GWTClientService")
public interface GWTClientService extends RemoteService {
    String loadUser(String username) throws IllegalArgumentException;
}
