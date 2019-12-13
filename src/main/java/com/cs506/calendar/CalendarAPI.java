package com.cs506.calendar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import com.cs506.workshop.WorkshopRequest;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;//
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

public final class CalendarAPI {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    
    public static Calendar getCalendar() throws GeneralSecurityException, IOException {
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        return service;
    }

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void createEvent(Calendar service, WorkshopRequest request) throws IOException {
        Event event = new Event()
                .setSummary("Workshop of " + request.getNameOfGroup())
                .setLocation("UW-Madison");
        
        	java.util.Calendar cal = new GregorianCalendar();
        	cal.setTime(request.getDate());
        	
        	LocalTime startTime = request.getStartTime();
        	LocalTime endTime = request.getEndTime();
        	
        	String startHour = String.valueOf(startTime.getHour());
        	if (startHour.length() == 1)
        		startHour = "0" + startHour;
        	String startMinute = String.valueOf(startTime.getMinute());
        	if (startMinute.length() == 1)
        		startMinute = "0" + startMinute;
        	
        	String endHour = String.valueOf(endTime.getHour());
        	if (endHour.length() == 1)
        		endHour = "0" + endHour;
        	String endMinute = String.valueOf(endTime.getMinute());
        	if (endMinute.length() == 1)
        		endMinute = "0" + endMinute;

            DateTime startDateTime = new DateTime(cal.get(java.util.Calendar.YEAR) + "-" + (cal.get(java.util.Calendar.MONTH) + 1) + "-" + cal.get(java.util.Calendar.DAY_OF_MONTH) + "T" + startHour + ":" + startMinute + ":00-06:00");
            EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Chicago");
            event.setStart(start);

            DateTime endDateTime = new DateTime(cal.get(java.util.Calendar.YEAR) + "-" + (cal.get(java.util.Calendar.MONTH) + 1) + "-" + cal.get(java.util.Calendar.DAY_OF_MONTH) + "T" + endHour + ":" + endMinute + ":00-06:00");
            EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Chicago");
            event.setEnd(end);

            EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("aljalaq@wisc.edu")
            };
            event.setAttendees(Arrays.asList(attendees));

            EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
            };
            Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
            event.setReminders(reminders);

            String calendarId = "primary";
            event = service.events().insert(calendarId, event).setSendUpdates("all").execute();
            System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

}
