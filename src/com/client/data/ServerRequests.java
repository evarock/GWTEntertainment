package com.client.data;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.Window;

public class ServerRequests {
    private static final String userServiceUrl = "http://localhost:8762/users/";

    public static void loadUser(String username) {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, userServiceUrl + username);
        builder.setHeader("Accept", "application/json");
//            builder.setHeader("Content-Type", "application/json");
//            builder.setRequestData(new JSONObject(jso).toString());
        try {
            builder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    String code = Integer.toString(response.getStatusCode());
                    if (!isSuccessfulResponse(code)) {
                        onError(request, new Throwable("Error received code:" + code));
                        return;
                    }
                    JSONObject responseJSON;
                    try {
                        responseJSON = JSONParser.parseStrict(response.getText()).isObject();
                    } catch (Exception e) {
                        onError(request, new Throwable("Failed to get response text:" + e.getMessage()));
                        return;
                    }
                    User user = User.parseJson(responseJSON);
                    if (user == null) {
                        onError(request, new Throwable("Failed to parse response"));
                        return;
                    }
                    ClientInfo.getInstance().setCurrentUser(user);
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    Window.alert("Error!" + exception.getMessage());
                }
            });
        }
        catch (RequestException e) {
            Window.alert("Request exception:" + e.getMessage());
        }
    }

    private static boolean isSuccessfulResponse(String code) {
        return code != null && !code.startsWith("4") && !code.startsWith("5");
    }
}
