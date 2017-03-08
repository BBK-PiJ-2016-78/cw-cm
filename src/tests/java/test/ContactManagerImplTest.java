package test; /**
 * Created by hradev01 on 09-Jan-17.
 */

import static org.junit.jupiter.api.Assertions.*;

import impl.*;
import org.junit.jupiter.api.Test;
import spec.*;

import java.io.*;
import java.util.*;

public class ContactManagerImplTest implements Serializable {

    private Calendar date = Calendar.getInstance(); // Get the current date
    private Set<Contact> contacts = new HashSet<>();
    private Set<Contact> contacts2 = new HashSet<>();
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
        manager.addFutureMeeting(contacts, date);
        int futureMeeting = manager.addFutureMeeting(contacts, date);
        FutureMeeting output = manager.getFutureMeeting(futureMeeting);
        FutureMeeting expected = new FutureMeetingImpl(3, date, contacts);
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
        contacts.add(contact);
        contacts2.add(contact2);
        Calendar testDate1 = Calendar.getInstance();
        Calendar testDate2 = Calendar.getInstance();
        manager.addNewContact("harry", "stuff");
        date.add(Calendar.DATE, 1);
        manager.addFutureMeeting(contacts2, date);
        testDate1.add(Calendar.DATE, 1);
        manager.addFutureMeeting(contacts, testDate1);
        manager.addFutureMeeting(contacts, testDate1);
        testDate2.add(Calendar.DATE, 2);
        manager.addFutureMeeting(contacts, testDate2);

        List<Meeting> output = manager.getFutureMeetingList(contact);

        assertEquals(5, output.get(0).getId());
        assertEquals(2, output.size());


    }

    @Test
    void getFutureMeetingListNullTest() {
        contacts.add(contact2);
        date.add(Calendar.DATE, 7);
        manager.addFutureMeeting(contacts, date);
        try {
            manager.getFutureMeetingList(contact);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {}
    }

    @Test
    void getMeetingListOnTest() {
        contacts.add(contact);
        contacts.add(contact2);
        Calendar testDate1 = Calendar.getInstance();
        Calendar testDate2 = Calendar.getInstance();
        date.add(Calendar.DATE, 1);
        manager.addFutureMeeting(contacts, date); // one meeting in future
        manager.addFutureMeeting(contacts, date); // another meeting in future (duplicate)
        testDate1.add(Calendar.DATE, -1);
        manager.addNewPastMeeting(contacts, testDate1, "some meeting");
        testDate2.add(Calendar.DATE, -2);
        manager.addNewPastMeeting(contacts, testDate2, "first meeting"); // two meetings in past on same date (duplicates)
        manager.addNewPastMeeting(contacts, testDate2, "second meeting");

        List<Meeting> output = manager.getMeetingListOn(testDate2);

        assertEquals(6, output.get(0).getId());
        assertEquals(1, output.size());
    }

    @Test
    void getPastMeetingListForTest() {

        contacts.add(contact);
        contacts2.add(contact2);
        Calendar testDate1 = Calendar.getInstance();
        Calendar testDate2 = Calendar.getInstance();
        manager.addNewContact("harry", "stuff");
        testDate1.add(Calendar.DATE, -1);
        manager.addNewPastMeeting(contacts2, testDate1, "another past meeting");
        manager.addNewPastMeeting(contacts, testDate1, "first meeting");
        manager.addNewPastMeeting(contacts, testDate1, "duplicate");
        testDate2.add(Calendar.DATE, -2);
        manager.addNewPastMeeting(contacts, testDate2, "second meeting");

        List<PastMeeting> output = manager.getPastMeetingListFor(contact);

        assertEquals(8, output.get(0).getId());
        assertEquals(6, output.get(1).getId());
        assertEquals(2, output.size());
    }

    @Test
    void getMeetingTest() {
        contacts.add(contact);
        contacts.add(contact2);
        date.add(Calendar.DATE, 5);
        int futureMeeting = manager.addFutureMeeting(contacts, date);
        date.add(Calendar.DATE, -6);
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

        Set<Contact> contactsSubset = new HashSet<>();
        List<Meeting> futureMeetingsData = new ArrayList<>();
        List<Meeting> pastMeetingsData = new ArrayList<>();

        String contactsSource = "./src/main/java/impl/contactsData.ser";
        String futureMeetingsSource = "./src/main/java/impl/futureMeetingsData.ser";
        String pastMeetingsSource = "./src/main/java/impl/pastMeetingsData.ser";

        manager.addNewContact("john", "stuff");
        manager.addNewContact("matt", "stuff");
        manager.addNewContact("bob", "stuff");

        contacts.add(contact2);
        date.add(Calendar.DATE, 7);
        manager.addFutureMeeting(contacts, date);
        manager.addFutureMeeting(contacts, date);
        manager.addFutureMeeting(contacts, date);

        date.add(Calendar.DATE, -10);
        manager.addNewPastMeeting(contacts, date, "past meeting");
        manager.addNewPastMeeting(contacts, date, "past meeting");

        manager.flush(); // Run the flush function to save all the data

        try (FileInputStream fis = new FileInputStream(contactsSource)) {

            ObjectInputStream ois = new ObjectInputStream(fis);
            contactsSubset = (Set<Contact>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e)  {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(futureMeetingsSource)) {

            ObjectInputStream ois = new ObjectInputStream(fis);
            futureMeetingsData = (List<Meeting>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e)  {
            e.printStackTrace();
        }


        try (FileInputStream fis = new FileInputStream(pastMeetingsSource)) {

            ObjectInputStream ois = new ObjectInputStream(fis);
            pastMeetingsData = (List<Meeting>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e)  {
            e.printStackTrace();
        }

        List<Contact> checkContacts = new ArrayList<>(contactsSubset);
        assertEquals(3, checkContacts.size());

        assertEquals(3, futureMeetingsData.size());
        assertEquals(5, futureMeetingsData.get(1).getId());
        assertEquals(7, futureMeetingsData.get(2).getId());

        assertEquals(2, pastMeetingsData.size());
        assertEquals(2, pastMeetingsData.get(0).getId());
        assertEquals(4, pastMeetingsData.get(1).getId());

    }
}