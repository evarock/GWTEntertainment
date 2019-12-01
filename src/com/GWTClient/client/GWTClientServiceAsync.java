package com.GWTClient.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTClientServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
