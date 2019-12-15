package com.client.widgets;

import com.client.data.Auth;
import com.client.data.ServerRequests;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class AuthPopup extends PopupPanel {
    private TextBox username = new TextBox();
    private PasswordTextBox password = new PasswordTextBox();

    public AuthPopup(boolean isAuth) {
        super();
        setModal(true);
        setAnimationEnabled(true);

        FlexTable content = new FlexTable();
        content.setSize("400px", "250px");
        int row = 0;

        String action = isAuth ? "Sign in" : "Sign up";
        HTML header = new HTML("<h3>" + action + "</h3>");
        content.setWidget(row, 0, header);
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
        content.getFlexCellFormatter().setColSpan(row, 0, 2);
        content.getFlexCellFormatter().setHeight(row, 0, "50px");
        row++;

        content.setWidget(row, 0, new HTML("Username:"));
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
        username.setWidth("250px");
        content.setWidget(row, 1, username);
        content.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
        row++;

        content.setWidget(row, 0, new HTML("Password:"));
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
        password.setWidth("250px");
        content.setWidget(row, 1, password);
        content.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
        row++;

        HorizontalPanel buttons = new HorizontalPanel();
        buttons.setWidth("100%");
        Button submit = new Button(action);
        submit.addClickHandler(event -> act(isAuth));
        Button close = new Button("Close");
        close.addClickHandler(event -> hide());
        buttons.add(submit);
        buttons.add(close);
        buttons.setCellHorizontalAlignment(submit, HasHorizontalAlignment.ALIGN_CENTER);
        buttons.setCellHorizontalAlignment(close, HasHorizontalAlignment.ALIGN_CENTER);
        content.setWidget(row, 0, buttons);
        content.getFlexCellFormatter().setColSpan(row, 0, 2);
        content.getColumnFormatter().setWidth(0, "100px");

        add(content);
        setStylePrimaryName("auth-popup");
        center();
    }

    private void act(boolean isAuth) {
        if (username.getValue() == null || username.getValue().isEmpty()) {
            Window.alert("Username is empty!");
            return;
        }
        if (password.getValue() == null || password.getValue().isEmpty()) {
            Window.alert("Password is empty!");
            return;
        }
        Auth auth = new Auth();
        auth.setUsername(username.getValue());
        auth.setPassword(password.getValue());
        String jsonStr = Auth.createJson(auth);
        if (jsonStr == null) {
            Window.alert("Error! Failed to create json!");
        } else if (isAuth) {
            ServerRequests.loadUser(jsonStr);
        } else {
            ServerRequests.registerUser(jsonStr);
        }
        hide();
    }
}
