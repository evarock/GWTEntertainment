package com.client.data;

import com.client.GWTClientService;
import com.client.GWTClientServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ServerRequests {
    private static GWTClientServiceAsync service = GWT.create(GWTClientService.class);

    public static void loadUser(String jsonMsg) {
        service.loadUser(jsonMsg, createUserCallback());
    }

    public static void registerUser(String jsonMsg) {
        service.registerUser(jsonMsg, createUserCallback());
    }

    public static void updateUser(String jsonMsg) {
        service.updateUser(jsonMsg, createUserCallback());
    }

    public static void deleteUser(String username) {
        service.deleteUser(username, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error! " + caught.getMessage());
                ClientInfo.getInstance().setCurrentUser(null);
            }

            @Override
            public void onSuccess(Void result) {
                ClientInfo.getInstance().setCurrentUser(null);
            }
        });
    }

    private static AsyncCallback<String> createUserCallback() {
        return new AsyncCallback<String>() {
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
    }
}
