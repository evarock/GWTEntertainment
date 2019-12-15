package com.client.data;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.json.client.*;

import java.util.Date;

public class User {
    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }

    public static final DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
    private static final String USERNAME_KEY = "username";
    private static final String EMAIL_KEY = "email";
    private static final String PHONE_KEY = "phone";
    private static final String DOB_KEY = "dob";
    private static final String GENDER_KEY = "gender";
    private static final String INIT_DATE_KEY = "initDate";
    private static final String ORGANIZATION_KEY = "organization";

    private String username;
    private String email;
    private Integer phone;
    private Date dob;
    private Gender gender;
    private Date initDate;
    private Boolean isOrganization;

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Boolean getOrganization() {
        return isOrganization;
    }

    public void setOrganization(Boolean organization) {
        isOrganization = organization;
    }

    public static User parseJson(JSONObject json) {
        if (json == null) {
            return null;
        }
        User parsedUser = new User();
        try {
            JSONString usernameValue = json.get(USERNAME_KEY).isString();
            String username = usernameValue.stringValue();
            if (username == null || username.isEmpty()) {
                throw new Exception("");
            }
            parsedUser.setUsername(username);

            String email = null;
            JSONValue jsonValue = json.get(EMAIL_KEY);
            if (jsonValue != null && jsonValue.isString() != null) {
                email = jsonValue.isString().stringValue();
            }
            parsedUser.setEmail(email);

            Integer phone = null;
            jsonValue = json.get(PHONE_KEY);
            if (jsonValue != null && jsonValue.isNumber() != null) {
                JSONNumber number = jsonValue.isNumber();
                phone = (int) number.doubleValue();
            }
            parsedUser.setPhone(phone);

            Date dob = null;
            jsonValue = json.get(DOB_KEY);
            if (jsonValue != null && jsonValue.isString() != null) {
                dob = dateTimeFormat.parse(jsonValue.isString().stringValue());
            }
            parsedUser.setDob(dob);

            Gender gender = Gender.UNKNOWN;
            try {
                gender = Gender.valueOf(json.get(GENDER_KEY).isString().stringValue());
            } catch (Exception ignored) {}
            parsedUser.setGender(gender);

            Date initDate = dateTimeFormat.parse(json.get(INIT_DATE_KEY).isString().stringValue());
            parsedUser.setInitDate(initDate);

            Boolean isOrganization = json.get(ORGANIZATION_KEY).isBoolean().booleanValue();
            parsedUser.setOrganization(isOrganization);
        } catch (Exception e) {
            return null;
        }
        return parsedUser;
    }

    public static String createJson(User user) {
        if (user == null || user.getUsername() == null || user.getInitDate() == null) {
            return null;
        }
        JSONObject json = new JSONObject();
        try {
            json.put(USERNAME_KEY, new JSONString(user.getUsername()));
            json.put(EMAIL_KEY, new JSONString(user.getEmail()));
            json.put(PHONE_KEY, new JSONNumber(user.getPhone()));
            json.put(DOB_KEY, new JSONString(user.getDob() == null ? "" : dateTimeFormat.format(user.getDob())));
            json.put(GENDER_KEY, new JSONString(user.getGender() == null ? Gender.UNKNOWN.name() : user.getGender().name()));
            json.put(INIT_DATE_KEY, new JSONString(dateTimeFormat.format(user.getInitDate())));
            json.put(ORGANIZATION_KEY, JSONBoolean.getInstance(user.getOrganization()));
        } catch (Exception e) {
            return null;
        }
        return json.toString();
    }
}
