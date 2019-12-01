package com.GWTClient.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GWTClientService")
public interface GWTClientService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use GWTClientService.App.getInstance() to access static instance of GWTClientServiceAsync
     */
    public static class App {
        private static GWTClientServiceAsync ourInstance = GWT.create(GWTClientService.class);

        public static synchronized GWTClientServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
