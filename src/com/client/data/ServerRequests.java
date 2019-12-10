package com.client.data;

import com.client.GWTClientService;
import com.client.GWTClientServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ServerRequests {
    private static GWTClientServiceAsync service = GWT.create(GWTClientService.class);

    public static void loadUserByServer(String username) {
        AsyncCallback<String> callback = new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error! " + caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                JSONObject responseJSON;
                try {
                    responseJSON = JSONParser.parseStrict(result).isObject();
                } catch (Exception e) {
                    onFailure(new Throwable("Failed to get response text:" + e.getMessage()));
                    return;
                }
                User user = User.parseJson(responseJSON);
                if (user == null) {
                    onFailure(new Throwable("Failed to parse response"));
                    return;
                }
                ClientInfo.getInstance().setCurrentUser(user);
            }
        };
        service.loadUser(username, callback);
    }
}
