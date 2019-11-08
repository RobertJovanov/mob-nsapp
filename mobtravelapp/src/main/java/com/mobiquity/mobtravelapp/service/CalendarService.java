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
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.mobiquity.mobtravelapp.exception.MissingCredentialsException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CalendarService {

    private static HttpTransport httpTransport;

    /** Directory to store user credentials. */
    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".store/mob_calendar_app");

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static FileDataStoreFactory dataStoreFactory;

    public List<Event> getEvents(Credential credential){

        List<Event> eventsList = new ArrayList<>();
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

            Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName("MobCalendarApp").build();

            String pageToken = null;
            do {
                Events events = service.events().list("primary")
                        .setTimeMin(new DateTime(Date.from(Instant.now())))
                        .setPageToken(pageToken).execute();
                List<Event> items = events.getItems();
                pageToken = events.getNextPageToken();
                eventsList = items;
            } while (pageToken != null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventsList;
    }
}
