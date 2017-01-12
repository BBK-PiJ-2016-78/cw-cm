/**
 * Created by hradev01 on 09-Jan-17.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

class ContactManagerImplTest {

    Calendar date = new GregorianCalendar(2017, 01, 12);
    Set<Contact> contacts = new HashSet<>();
    Contact contact = new ContactImpl(1, "name", "stuff");

    @Test
    void addFutureMeetingTest() {
        contacts.add(contact);
        ContactManager manager = new ContactManagerImpl();
        int output = manager.addFutureMeeting(contacts, date);
        int expected = 1;
        assertEquals(expected, output);
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