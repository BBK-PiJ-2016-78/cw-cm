import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class MeetingImplTest {

    private Calendar date = new GregorianCalendar(2017, 01, 10);
    private Set<Contact> contacts = new HashSet<>();
    private Contact contact = new ContactImpl(1, "name", "");

    @Test
    public void constructorTest() {
        try {
            Meeting meeting = new MeetingImpl(1, date, contacts);
            fail("Expected IllegalArgumentException or NullPointerException");
        } catch (IllegalArgumentException | NullPointerException ex) {
            // if caught test Pass
        }
    }

    @Test
    public void getIdTest() {
        contacts.add(contact);
        Meeting meeting = new MeetingImpl(1, date, contacts);
        int output = meeting.getId();
        int expected = 1;
        assertEquals(expected, output);
    }

    @Test
    public void getDateTest() {
        contacts.add(contact);
        Meeting meeting = new MeetingImpl(1, date, contacts);
        Calendar output = meeting.getDate();
        Calendar expected = new GregorianCalendar(2017, 01, 10);
        assertEquals(expected, output);
    }

    @Test
    public void getContactsTest() {
        contacts.add(contact);
        Meeting meeting = new MeetingImpl(1, date, contacts);
        Set<Contact> output = meeting.getContacts();
        Set<Contact> expected = contacts;
        assertEquals(expected, output);
    }

}