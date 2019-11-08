package com.mobiquity.mobtravelapp.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class AuthorizationService {

    private static HttpTransport httpTransport;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static FileDataStoreFactory dataStoreFactory;

    /** Directory to store user credentials. */
    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".store/mob_calendar_app");

    /** Authorizes the installed application to access user's protected data. */
    Credential authorize() throws IOException{

        setUpHttpTransport();

        // load client secrets
        GoogleClientSecrets clientSecrets;
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                    new InputStreamReader(CalendarService.class.getResourceAsStream("/credentials.json")));

            if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                    || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
                System.out.println(
                        "Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar "
                                + "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
                System.exit(1);
            }
            // set up authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
                    .build();
        // authorize
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    private void setUpHttpTransport() throws IOException{
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            //TODO LOG
            e.printStackTrace();
        }
        dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
    }
}
