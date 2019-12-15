package com.client.widgets;

import com.client.data.ClientInfo;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;

public class HeaderTable extends FlexTable {
    private static HeaderTable instance;

    public static HeaderTable getInstance() {
        if (instance == null) {
            instance = new HeaderTable();
        }
        return instance;
    }

    public HeaderTable() {
        setWidth("100%");
        setCellPadding(0);
        setCellSpacing(0);
        HTML headerHTML = new HTML("User Facilities Portal");
        headerHTML.setStyleName("header-html");
        setWidget(0, 0, headerHTML);
        updateLayout();
    }

    private void initAuthLayout() {
        HorizontalPanel hp = new HorizontalPanel();
        HTML logout = new HTML("Logout");
        logout.addClickHandler(event -> ClientInfo.getInstance().setCurrentUser(null));
        logout.setStyleName("logout-button");
        hp.add(logout);
        hp.setCellVerticalAlignment(logout, HasVerticalAlignment.ALIGN_MIDDLE);
        Button profileButton = new Button("P");
        profileButton.addClickHandler(event -> new ProfilePopup());
        profileButton.setStylePrimaryName("profile-html");
        hp.add(profileButton);
        setWidget(0, 1, hp);
        getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    }

    public void updateLayout() {
        if (ClientInfo.getInstance().getCurrentUser() != null) {
            initAuthLayout();
        } else {
            initCommonLayout();
        }
    }

    private void initCommonLayout() {
        HorizontalPanel hp = new HorizontalPanel();
        HTML signin = new HTML("Sign in");
        signin.addClickHandler(event -> new AuthPopup(true));
        signin.setStyleName("logout-button");
        hp.add(signin);
        hp.setCellVerticalAlignment(signin, HasVerticalAlignment.ALIGN_MIDDLE);
        HTML signup = new HTML("Sign up");
        signup.addClickHandler(event -> new AuthPopup(false));
        signup.setStyleName("logout-button");
        hp.add(signup);
        hp.setCellVerticalAlignment(signup, HasVerticalAlignment.ALIGN_MIDDLE);
        setWidget(0, 1, hp);
        getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    }
}
