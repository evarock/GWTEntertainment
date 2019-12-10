package com.client.widgets;

import com.client.data.ClientInfo;
import com.client.data.User;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.*;

import java.util.Date;

public class ProfilePopup extends PopupPanel {
    private TextBox email = new TextBox();
    private IntegerBox phone = new IntegerBox();
    private DateBox dob = new DateBox();
    private RadioButton male = new RadioButton("MALE");
    private RadioButton female = new RadioButton("FEMALE");
    private CheckBox isOrganization = new CheckBox(" Organization");

    public ProfilePopup() {
        super();
        User user = ClientInfo.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        setModal(true);
        setAnimationEnabled(true);

        FlexTable content = new FlexTable();
        content.setSize("500px", "450px");
        int row = 0;

        HTML header = new HTML("<h3>Edit '" + user.getUsername() + "' Profile</h3>");
        content.setWidget(row, 0, header);
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
        content.getFlexCellFormatter().setColSpan(row, 0, 2);
        content.getFlexCellFormatter().setHeight(row, 0, "50px");
        row++;

        content.setWidget(row, 0, new HTML("Registration Date:"));
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
        String iniDateStr = user.getInitDate() != null ? user.getInitDate().toString() : "";
        content.setWidget(row, 1, new HTML(iniDateStr));
        content.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
        row++;

        content.setWidget(row, 0, new HTML("Email:"));
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
        email.setWidth("250px");
        content.setWidget(row, 1, email);
        row++;

        content.setWidget(row, 0, new HTML("Phone:"));
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
        phone.setWidth("250px");
        content.setWidget(row, 1, phone);
        row++;

        DatePicker datePicker = new DatePicker();
        DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM-dd-yyyy");
        dob.setWidth("100px");
        dob.setFormat(new DateBox.DefaultFormat(dateFormat));
        datePicker.addValueChangeHandler(event -> {
            Date date = event.getValue();
            dob.setValue(date);
        });
        datePicker.setValue(new Date(), true);
        HorizontalPanel datePanel = new HorizontalPanel();
        datePanel.add(dob);
        dob.getElement().getStyle().setMarginRight(15, Style.Unit.PX);
        datePanel.add(datePicker);
        HTML dobHTML = new HTML("Date of birth:");
        dobHTML.getElement().getStyle().setMarginTop(7, Style.Unit.PX);
        content.setWidget(row, 0, dobHTML);
        content.getFlexCellFormatter().setAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
        content.setWidget(row, 1, datePanel);
        row++;

        male.setText("Male");
        content.setWidget(row, 0, male);
        male.addValueChangeHandler(event -> female.setValue(false));
        content.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
        female.setText("Female");
        female.addValueChangeHandler(event -> male.setValue(false));
        content.setWidget(row, 1, female);
        content.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
        row++;

        content.setWidget(row, 1, isOrganization);
        content.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
        row++;

        HorizontalPanel buttons = new HorizontalPanel();
        buttons.setWidth("100%");
        Button submit = new Button("Submit");
        submit.addClickHandler(event -> saveProfile());
        Button close = new Button("Close");
        close.addClickHandler(event -> hide());
        buttons.add(submit);
        buttons.add(close);
        buttons.setCellHorizontalAlignment(submit, HasHorizontalAlignment.ALIGN_CENTER);
        buttons.setCellHorizontalAlignment(close, HasHorizontalAlignment.ALIGN_CENTER);
        content.setWidget(row, 0, buttons);
        content.getFlexCellFormatter().setColSpan(row, 0, 2);
        content.getColumnFormatter().setWidth(0, "150px");

        add(content);
        setStylePrimaryName("edit-user-popup");
        center();
        initData();
    }

    private void initData() {
        User user = ClientInfo.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        email.setValue(user.getEmail());
        phone.setValue(user.getPhone());
        dob.setValue(user.getDob());
        User.Gender gender = user.getGender();
        if (User.Gender.FEMALE.equals(gender)) {
            female.setValue(true, true);
        } else if (User.Gender.MALE.equals(gender)) {
            male.setValue(true, true);
        }
        isOrganization.setValue(user.getOrganization());
    }

    private void saveProfile() {
        int phoneValue = -1;
        try {
            phoneValue = phone.getValue();
        } catch (Exception e) {
            Window.alert("Invalid phone!");
            return;
        }
        User user = ClientInfo.getInstance().getCurrentUser();
        user.setEmail(email.getValue());
        user.setPhone(phoneValue);
        user.setDob(dob.getValue());
        if (female.getValue() != null && female.getValue()) {
            user.setGender(User.Gender.FEMALE);
        } else if (male.getValue() != null && male.getValue()) {
            user.setGender(User.Gender.MALE);
        }
        user.setOrganization(isOrganization.getValue());
        hide();
    }
}
