package com.wffwebdemo.minimalproductionsample.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.wffwebdemo.minimalproductionsample.server.constants.ServerConstants;

public class HeartBeatUtil {

    private static final Logger LOGGER = Logger
            .getLogger(HeartBeatUtil.class.getName());

    public static void ping(final String sessionId) {

        if (ServerConstants.CONTEXT_PATH == null) {
            return;
        }

        // TODO this code may be improved
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                HttpURLConnection con = null;
                BufferedReader in = null;
                try {
                    String url = ServerConstants.DOMAIN_URL
                            .concat(ServerConstants.CONTEXT_PATH)
                            .concat("/heart-beat");

                    URL obj = new URL(url);
                    con = (HttpURLConnection) obj.openConnection();

                    // optional default is GET
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Cookie", "JSESSIONID=" + sessionId);
                    con.connect();

                    int responseCode = con.getResponseCode();

                    LOGGER.info("responseCode " + responseCode);

                    in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    LOGGER.info("heartbeat response " + response);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }

}
