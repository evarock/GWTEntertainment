package com.GWTClient.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.GWTClient.client.GWTClientService;

public class GWTClientServiceImpl extends RemoteServiceServlet implements GWTClientService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}