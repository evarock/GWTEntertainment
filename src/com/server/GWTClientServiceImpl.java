package com.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.client.GWTClientService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GWTClientServiceImpl extends RemoteServiceServlet implements GWTClientService {
    private static final String userServiceUrl = "http://localhost:8762/users/";

    public String loadUser(String username) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con = null;
        InputStream inStream = null;
        BufferedReader reader = null;
        Exception innerException = null;
        try {
            con = (HttpURLConnection) new URL(userServiceUrl + username).openConnection();
            con.setDoOutput(false);
            con.setDoInput(true);
            con.setAllowUserInteraction(true);
            con.setUseCaches(false);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("charset", "utf-8");
            inStream = con.getResponseCode() < 400 ? con.getInputStream() : con.getErrorStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            innerException = e;
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                    inStream = null;
                }
            } catch (Exception ignored) {}
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (Exception ignored) {}
            try {
                if (innerException != null && con != null) {
                    con.disconnect();
                    con = null;
                }
            } catch (Exception ignored) {}
        }
        if (innerException != null) {
            return innerException.getMessage();
        }
        return sb.toString();
    }

    public String updateUser(String jsonMsg) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con = null;
        OutputStream outStream = null;
        InputStream inStream = null;
        BufferedReader reader = null;
        Exception innerException = null;
        try {
            con = (HttpURLConnection) new URL(userServiceUrl).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setAllowUserInteraction(true);
            con.setUseCaches(false);
//            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(jsonMsg.length()));
            outStream = con.getOutputStream();
            outStream.write(jsonMsg.getBytes(StandardCharsets.UTF_8));
            outStream.flush();
            outStream.close();
            outStream = null;
            inStream = con.getResponseCode() < 400 ? con.getInputStream() : con.getErrorStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            innerException = e;
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                    outStream = null;
                }
            } catch (Exception ignored) { }
            try {
                if (inStream != null) {
                    inStream.close();
                    inStream = null;
                }
            } catch (Exception ignored) {}
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (Exception ignored) {}
            try {
                if (innerException != null && con != null) {
                    con.disconnect();
                    con = null;
                }
            } catch (Exception ignored) {}
        }
        if (innerException != null) {
            return innerException.getMessage();
        }
        return sb.toString();
    }
}