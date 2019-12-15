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
    private static final String authServiceUrl = "http://localhost:8762/auth/";

    @Override
    public String updateUser(String jsonMsg) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con = null;
        OutputStream outStream = null;
        InputStream inStream = null;
        BufferedReader reader = null;
        Exception innerException = null;
        boolean hasError = false;
        try {
            con = (HttpURLConnection) new URL(userServiceUrl).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("PUT");
            con.setAllowUserInteraction(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(jsonMsg.length()));
            outStream = con.getOutputStream();
            outStream.write(jsonMsg.getBytes(StandardCharsets.UTF_8));
            outStream.flush();
            outStream.close();
            outStream = null;
            hasError = con.getResponseCode() >= 400;
            inStream = !hasError ? con.getInputStream() : con.getErrorStream();
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
                }
            } catch (Exception ignored) { }
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (Exception ignored) {}
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ignored) {}
            try {
                if (innerException != null && con != null) {
                    con.disconnect();
                }
            } catch (Exception ignored) {}
        }
        if (innerException != null) {
            throw new IllegalArgumentException(innerException.getMessage());
        }
        if (hasError) {
            throw new IllegalArgumentException(sb.toString());
        }
        return sb.toString();
    }

    @Override
    public void deleteUser(String username) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con = null;
        InputStream inStream = null;
        BufferedReader reader = null;
        Exception innerException = null;
        boolean hasError = false;
        try {
            con = (HttpURLConnection) new URL(authServiceUrl + username).openConnection();
            con.setDoOutput(false);
            con.setDoInput(true);
            con.setAllowUserInteraction(true);
            con.setUseCaches(false);
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("charset", "utf-8");
            hasError = con.getResponseCode() >= 400;
            inStream = !hasError ? con.getInputStream() : con.getErrorStream();
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
                }
            } catch (Exception ignored) {}
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ignored) {}
            try {
                if (innerException != null && con != null) {
                    con.disconnect();
                }
            } catch (Exception ignored) {}
        }
        if (innerException != null) {
            throw new IllegalArgumentException(innerException.getMessage());
        }
        if (hasError) {
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override
    public String loadUser(String jsonMessage) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con = null;
        OutputStream outStream = null;
        InputStream inStream = null;
        BufferedReader reader = null;
        Exception innerException = null;
        boolean hasError = false;
        try {
            con = (HttpURLConnection) new URL(authServiceUrl + "get/").openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(jsonMessage.length()));
            outStream = con.getOutputStream();
            outStream.write(jsonMessage.getBytes(StandardCharsets.UTF_8));
            outStream.flush();
            outStream.close();
            outStream = null;
            hasError = con.getResponseCode() >= 400;
            inStream = !hasError ? con.getInputStream() : con.getErrorStream();
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
                }
            } catch (Exception ignored) { }
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (Exception ignored) {}
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ignored) {}
            try {
                if (innerException != null && con != null) {
                    con.disconnect();
                }
            } catch (Exception ignored) {}
        }
        if (innerException != null) {
            throw new IllegalArgumentException(innerException.getMessage());
        }
        if (hasError) {
            throw new IllegalArgumentException(sb.toString());
        }
        return sb.toString();
    }

    @Override
    public String registerUser(String jsonMessage) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con = null;
        OutputStream outStream = null;
        InputStream inStream = null;
        BufferedReader reader = null;
        Exception innerException = null;
        boolean hasError = false;
        try {
            con = (HttpURLConnection) new URL(authServiceUrl + "add/").openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(jsonMessage.length()));
            outStream = con.getOutputStream();
            outStream.write(jsonMessage.getBytes(StandardCharsets.UTF_8));
            outStream.flush();
            outStream.close();
            outStream = null;
            hasError = con.getResponseCode() >= 400;
            inStream = !hasError ? con.getInputStream() : con.getErrorStream();
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
                }
            } catch (Exception ignored) { }
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (Exception ignored) {}
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ignored) {}
            try {
                if (innerException != null && con != null) {
                    con.disconnect();
                }
            } catch (Exception ignored) {}
        }
        if (innerException != null) {
            throw new IllegalArgumentException(innerException.getMessage());
        }
        if (hasError) {
            throw new IllegalArgumentException(sb.toString());
        }
        return sb.toString();
    }
}