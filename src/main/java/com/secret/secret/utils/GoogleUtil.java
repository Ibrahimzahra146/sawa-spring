package com.secret.secret.utils;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.logging.Logger;
	
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
	
import org.apache.catalina.Authenticator;
import org.apache.catalina.connector.Request;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
	public class GoogleUtil  {
		
	

		public String  getGoogleUserId(String token) throws GeneralSecurityException, IOException {
			 String userId=null;
			 HttpTransport transport=new ApacheHttpTransport();;
			 JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			 GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				    .setAudience(Collections.singletonList(Constants.ANDROID_CLIENT_ID))
				    // Or, if multiple clients access the backend:
				    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				    .build();

				// (Receive idTokenString by HTTPS POST)
				//String token1="eyJhbGciOiJSUzI1NiIsImtpZCI6IjU2YThjYTgxZjc2MDBlMWRjOWMzY2ZkYmQwZjAyNTFiMTY2OTE5OGUifQ.eyJhenAiOiI5NjY5MjQxOTUyLWVwYTFiOTdkODMzazQwcDUwOGhlNWxhZ2UwdmpsbjlyLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiOTY2OTI0MTk1Mi1oMWppYTRmaGFtaDcwaTdqOHM0MDlwN2Yybzk1c2NyNC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNzAzMDI5ODQwMTE5NjUxMzIwMSIsImVtYWlsIjoiaWJyYWhpbS56YWhyYS4xNjZAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImlzcyI6Imh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbSIsImlhdCI6MTUwMDMyMTgxOSwiZXhwIjoxNTAwMzI1NDE5LCJuYW1lIjoiSWJyYWhpbSBaYWhyYSIsInBpY3R1cmUiOiJodHRwczovL2xoNi5nb29nbGV1c2VyY29udGVudC5jb20vLTlOU2RoNThtOE0wL0FBQUFBQUFBQUFJL0FBQUFBQUFBQUVvL19wSG1sMUNfNl8wL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJJYnJhaGltIiwiZmFtaWx5X25hbWUiOiJaYWhyYSIsImxvY2FsZSI6ImVuIn0.JxVHBfk6yMr0ZdsBRzMsuMcvBtn5GF3Uis-lehGYybkMcYmsKshg8Ht-HAEeN4HTTrwfZs1688nUfstFFa-dG575HcjgOu-fJuRunH0XS6PhBQNU7wIqZ9xOm2ckBBbL0zdB-yZzMf4E1kdE0EUADrnMAeXpJSomDYmE_v7P9phToQtQcvOQG69jN78T_4TrYz3VCqFqDvUMuRhLjRlAneMCa31npmfeC_kmrZLfck4kz0Ij2kNrPZvMky0lJBP7OX2GoOSnWiwdooBAsFJLmWq5aFzOcpYdYwIl5rTKzuEOj2kzpN5FWj3nkDUzZL84Dqqc6oAFi5KZXDhJLnKmtg";

				GoogleIdToken idToken = verifier.verify(token);
				if (idToken != null) {
				  Payload payload = idToken.getPayload();

				  // Print user identifier
				   userId = payload.getSubject();
				  System.out.println("User ID: " + userId);

				  // Get profile information from payload
				  String email = payload.getEmail();
				  System.out.println("User ID: " + email);

				  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
				  String name = (String) payload.get("name");
				  String pictureUrl = (String) payload.get("picture");
				  String locale = (String) payload.get("locale");
				  String familyName = (String) payload.get("family_name");
				  String givenName = (String) payload.get("given_name");

				  // Use or store profile information
				  // ...

				} else {
				  System.out.println("Invalid ID token.");
				}
				return userId;
			 }
	}
