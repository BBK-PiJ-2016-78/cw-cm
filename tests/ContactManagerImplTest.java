/**
 * Created by hradev01 on 09-Jan-17.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ContactManagerImplTest {

    Calendar date = new GregorianCalendar(2017, 00, 13);
    Set<Contact> contacts = new HashSet<>();
    Contact contact = new ContactImpl(1, "harry", "stuff");
    Contact contact2 = new ContactImpl(2, "larry", "some notes");

    @Test
    void addFutureMeetingTest() {
        contacts.add(contact);
        contacts.add(contact2);
        ContactManager manager = new ContactManagerImpl();
        manager.addFutureMeeting(contacts, date);
        int output = manager.addFutureMeeting(contacts, date);
        int expected = 2;
        assertEquals(expected, output);
    }

    @Test
    void addFutureMeetingIllegalArgument() {
        contacts.add(contact);
        contacts.add(null);
        try {
            ContactManager manager = new ContactManagerImpl();
            manager.addFutureMeeting(contacts, date);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void addFutureMeetingIllegalArgument2() {
        contacts.add(contact);
        contacts.add(contact2);
        try {
            ContactManager manager = new ContactManagerImpl();
            manager.addFutureMeeting(contacts, null);
            fail("Expected NullPointerException");

        } catch (NullPointerException ex) {}

    }

    @Test
    void addFutureMeetingNullTest() {
        contacts.add(contact);
        contacts.add(contact2);
        try {
            Calendar pastDate = new GregorianCalendar(2017, 00, 10);
            ContactManager manager = new ContactManagerImpl();
            manager.addFutureMeeting(contacts, pastDate);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}

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