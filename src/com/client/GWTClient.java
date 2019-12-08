package com.client;

import com.client.widgets.HeaderTable;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class GWTClient implements EntryPoint {
    @Override
    public void onModuleLoad() {
        AbsolutePanel header = new AbsolutePanel();
        header.getElement().getStyle().setBackgroundColor("#41428d");
        header.add(HeaderTable.getInstance());
        AbsolutePanel main = new AbsolutePanel();
        main.getElement().getStyle().setBackgroundColor("#D4D7ED");
        AbsolutePanel footer = new AbsolutePanel();
        footer.getElement().getStyle().setBackgroundColor("#41428d");
        HTML footerHTML = new HTML("2019 © FacilitiesPortal");
        footerHTML.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        footerHTML.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        footerHTML.getElement().getStyle().setMarginTop(18, Style.Unit.PX);
        footer.add(footerHTML);
        RootPanel.get().clear();
        RootLayoutPanel.get().add(header);
        RootLayoutPanel.get().setWidgetTopHeight(header, 0, Style.Unit.PX, 80, Style.Unit.PX);
        RootLayoutPanel.get().add(main);
        int clientHeight = Window.getClientHeight();
        RootLayoutPanel.get().setWidgetTopHeight(main, 80, Style.Unit.PX, clientHeight - 130, Style.Unit.PX);
        RootLayoutPanel.get().add(footer);
        RootLayoutPanel.get().setWidgetBottomHeight(footer, 0, Style.Unit.PX, 50, Style.Unit.PX);
        //ServerRequests.loadUser("root");
    }
}
