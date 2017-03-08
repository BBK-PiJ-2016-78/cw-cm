package test;

import impl.ContactImpl;
import impl.PastMeetingImpl;
import org.junit.jupiter.api.Test;
import spec.Contact;
import spec.PastMeeting;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public class PastMeetingImplTest {

    Calendar date = new GregorianCalendar(2017, 01, 10);
    Set<Contact> contacts = new HashSet<>();
    Contact contact = new ContactImpl(1, "name", "stuff");
    String notes = "new notes";

    @Test
    public void constructorTest() {
        try {
            PastMeeting meet = new PastMeetingImpl(1, date, contacts, notes);
            fail("Expected IllegalArgumentException or NullPointerException");
        } catch (IllegalArgumentException | NullPointerException ex) {
            // if caught test Pass
        }
    }

    @Test
    void getNotesTest() {
        contacts.add(contact);
        PastMeeting meet = new PastMeetingImpl(1, date, contacts, notes);
        String output = meet.getNotes();
        String expected = "new notes";
        assertEquals(expected, output);
    }

}