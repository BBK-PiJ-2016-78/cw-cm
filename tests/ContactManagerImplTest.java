/**
 * Created by hradev01 on 09-Jan-17.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ContactManagerImplTest {

    private Calendar date = Calendar.getInstance(); // Get the current date
    private Set<Contact> contacts = new HashSet<>();
    private Contact contact = new ContactImpl(1, "harry", "stuff");
    private Contact contact2 = new ContactImpl(2, "larry", "some notes");
    private ContactManager manager = new ContactManagerImpl();

    @Test
    void addFutureMeetingTest() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, 5); // Add 5 days to the current date
        manager.addFutureMeeting(contacts, date);
        int output = manager.addFutureMeeting(contacts, date);
        int expected = 2;
        assertEquals(expected, output);
    }

    @Test
    void addFutureMeetingIllegalArgument() {
        contacts.add(contact);
        contacts.add(null);
        date.add(Calendar.DATE, 5);
        try {
            manager.addFutureMeeting(contacts, date);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void addFutureMeetingIllegalArgument2() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, -5); // Subtract 5 days from the current date
        try {
            manager.addFutureMeeting(contacts, date);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void addFutureMeetingNullTest() {
        contacts.add(contact);
        contacts.add(contact2);
        try {
            manager.addFutureMeeting(contacts, null);
            fail("Expected NullPointerException");

        } catch (NullPointerException ex) {}

    }

    @Test
    void getPastMeetingTest() {

    }

    @Test
    void getFutureMeetingTest() {

    }

    @Test
    void getFutureMeetingListTest() {

    }

    @Test
    void getMeetingListOnTest() {

    }

    @Test
    void getPastMeetingListForTest() {

    }

    @Test
    void addNewPastMeetingTest() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, -5); // Subtract 5 days from the current date
        manager.addNewPastMeeting(contacts, date, "past meeting");
        int output = manager.addNewPastMeeting(contacts, date, "past meeting");
        int expected = 2;
        assertEquals(expected, output);
    }

    @Test
    void addNewPastMeetingIllegalArgument() {
        contacts.add(contact);
        contacts.add(null);
        date.add(Calendar.DATE, -5);
        try {
            manager.addNewPastMeeting(contacts, date, "past meeting");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void addNewPastMeetingIllegalArgument2() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, 5); // Add 5 days in future
        try {

            manager.addNewPastMeeting(contacts, date, "past meeting");
            fail("Expected IllegalArgumentException");

        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void addNewPastMeetingIllegalArgument3() { // Testing for empty contacts
        date.add(Calendar.DATE, -5);
        try {
            manager.addNewPastMeeting(contacts, date, "past meeting");
            fail("Expected IllegalArgumentException");

        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void addNewPastMeetingNullTest() {
        date.add(Calendar.DATE, -5);
        try {
            manager.addNewPastMeeting(null, date, "some notes");
            fail("Expected NullPointerException");
        } catch (NullPointerException ex) {}

    }

    @Test
    void addMeetingNotesTest() {

    }

    @Test
    void addNewContactTest() {

    }

    @Test
    void getContacts1Test() {

    }

    @Test
    void getContacts2Test() {

    }

    @Test
    void flushTest() {

    }

}