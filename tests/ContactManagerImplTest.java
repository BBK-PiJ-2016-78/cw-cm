/**
 * Created by hradev01 on 09-Jan-17.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
        manager.addFutureMeeting(contacts, date);
        int output = manager.addFutureMeeting(contacts, date);
        int expected = 5;
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
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, -5);
        int pastMeeting = manager.addNewPastMeeting(contacts, date, "past meeting");
        PastMeeting output = manager.getPastMeeting(pastMeeting);
        PastMeeting expected = new PastMeetingImpl(2, date, contacts, "some notes");
        assertEquals(expected.getId(), output.getId());
    }

    @Test
    void getPastMeetingIllegalState() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, 5);
        int futureMeeting = manager.addFutureMeeting(contacts, date);
        try {
            manager.getPastMeeting(futureMeeting);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException ex) {}
    }

    @Test
    void getFutureMeetingTest() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, 5);
        int futureMeeting = manager.addFutureMeeting(contacts, date);
        FutureMeeting output = manager.getFutureMeeting(futureMeeting);
        FutureMeeting expected = new FutureMeetingImpl(1, date, contacts);
        assertEquals(expected.getId(), output.getId());
    }

    @Test
    void getFutureMeetingIllegalState() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, -5);
        int pastMeeting = manager.addNewPastMeeting(contacts, date, "some notes");
        try {
            manager.getFutureMeeting(pastMeeting);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException ex) {}
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
    void getMeetingTest() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, 5);
        int futureMeeting = manager.addFutureMeeting(contacts, date);
        date.add(Calendar.DATE, -5);
        int pastMeeting = manager.addNewPastMeeting(contacts, date, "past meeting");
        Meeting output = manager.getMeeting(futureMeeting); // pastMeeting can be used too
        Meeting expected = new MeetingImpl(1, date, contacts); // change 2 for pastMeeting
        assertEquals(expected.getId(), output.getId());
    }

    @Test
    void addNewPastMeetingTest() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, -5); // Subtract 5 days from the current date
        manager.addNewPastMeeting(contacts, date, "past meeting");
        manager.addNewPastMeeting(contacts, date, "past meeting");
        int output = manager.addNewPastMeeting(contacts, date, "past meeting");
        int expected = 6;
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
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, -5);
        manager.addNewPastMeeting(contacts, date, "past meeting");
        PastMeeting output = manager.addMeetingNotes(2, "new notes");
        PastMeeting expected = manager.getPastMeeting(2);
        assertEquals(expected.getNotes(), output.getNotes());
    }

    @Test
    void addMeetingNotesIllegalArgument() {
        try {
            manager.addMeetingNotes(2, "new notes");
            fail("Expected IllegalArgumentException");
        } catch(IllegalArgumentException ex) {}
    }

    @Test
    void addMeetingNotesIllegalState() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, 5);
        manager.addFutureMeeting(contacts, date);
        try {
            manager.addMeetingNotes(1, "new notes");
            fail("Expected IllegalStateException");
        } catch(IllegalStateException ex) {}
    }

    @Test
    void addMeetingNotesNullException() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, -5);
        manager.addNewPastMeeting(contacts, date, "past meeting");
        try {
            manager.addMeetingNotes(2, null);
            fail("Expected NullPointerException");
        } catch(NullPointerException ex) {}
    }

    @Test
    void addNewContactTest() {
        String name = "Javarenkov";
        String notes = "cool guy";
        manager.addNewContact(name, notes);
        int output = manager.addNewContact(name, notes);
        int expected = 2;
        assertEquals(expected, output);
    }

    @Test
    void addNewContactIllegalArgument() {
        String name = "";
        String notes = "cool guy";
        try {
            manager.addNewContact(name, notes);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void addNewContactNullTest() {
        String name = "Javarenkov";
        try {
            manager.addNewContact(name, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException ex) {}
    }

    @Test
    void getContacts1Test() {
        manager.addNewContact("larry", "some notes");
        manager.addNewContact("larry", "some notes");
        manager.addNewContact("harry", "some notes");

        Set<Contact> output = manager.getContacts("larry");

        Set<Contact> expected = new HashSet<>();
        expected.add(contact2);
        expected.add(contact2);

        assertEquals(expected.contains(contact2.getName()), output.contains(contact2.getName()));
        assertEquals(2, output.size());
    }

    @Test
    void getContacts1GetAll() {
        manager.addNewContact("larry", "some notes");
        manager.addNewContact("larry", "some notes");
        manager.addNewContact("harry", "some notes");
        contacts.add(contact);
        contacts.add(contact2);
        contacts.add(contact2);

        Set<Contact> output = manager.getContacts("");
        Set<Contact> expected = contacts;
        assertEquals(expected.contains(contact.getName()), output.contains(contact.getName()));
        assertEquals(3, output.size());
    }

    @Test
    void getContacts2Test() {
        manager.addNewContact("larry", "some notes");
        manager.addNewContact("larry", "some notes");
        manager.addNewContact("harry", "some notes");

        Set<Contact> output = manager.getContacts(1, 2, 3);

        Set<Contact> expected = new HashSet<>();
        expected.add(contact2);
        expected.add(contact2);
        expected.add(contact);

        assertEquals(expected.contains(contact2.getId()), output.contains(contact2.getId()));
        assertEquals(3, output.size());
    }

    @Test
    void getContacts2ExceptionTest() {
        try {
            manager.getContacts();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}

    }

    @Test
    void flushTest() {

    }

}