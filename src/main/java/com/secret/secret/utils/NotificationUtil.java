package com.secret.secret.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class NotificationUtil {
	public final static String AUTH_KEY_FCM = "AAAAAkBU7GA:APA91bGZ-f86xaNgu-fcOqCLORNE_KKByr6o4jCWfaUnkui5nXL6qcBSzRcHNTBQY0npyIPMh2XeJM41CgWOJO-3rzu7IeVZEMXH8vOo0gjpp-USAs5AkW6PvuI-aU3Hp1K_Urs5UzzB";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static  String sendPushNotification(String deviceToken)
	        throws IOException, JSONException {
	    String result = "";
	   // deviceToken="dGj1DYHZXec:APA91bHw78YJhAnggK8mPQLJnw7RbfsnKeoAPycuhGk8fR3YcJGnGBd7SoaILWwFxJKE0Kjq1skOHZdOAdYX7X5CIBP8P4EUKhycmfT8W3giK8u0LUH1Zh5a8L2QYCVystXskkvwQe5Z";
	   /*
	    * RestTemplate restTemplate = new RestTemplate();
  		 ResponseEntity<String> response = restTemplate.postForEntity(Constants.birthDayURL, employees, String.class);
	    * */
	    URL url = new URL(API_URL_FCM);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);

	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
	    conn.setRequestProperty("Content-Type", "application/json");

	    JSONObject json = new JSONObject();

	    json.put("to", deviceToken.trim());
	    JSONObject info = new JSONObject();
	    info.put("title", "notification title"); // Notification title
	    info.put("body", "message body"); // Notification
	                                                            // body
	    json.put("notification", info);
	    try {
	        OutputStreamWriter wr = new OutputStreamWriter(
	                conn.getOutputStream());
	        wr.write(json.toString());
	        wr.flush();

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	        }
	        result = "success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = "failur";
	    }
	    System.out.println("GCM Notification is sent successfully");

	    return result;
 }
}
