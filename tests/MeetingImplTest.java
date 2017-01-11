import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class MeetingImplTest {

    private Calendar date = new GregorianCalendar(2017, 01, 10);
    private Set<Contact> contacts = new HashSet<>();
    private Contact contact = new ContactImpl(1, "name", "");
    private Meeting meeting = new MeetingImpl(1, date, contacts);


    @Test
    public void getIdTest() {
        contacts.add(contact);
        int output = meeting.getId();
        int expected = 1;
        assertEquals(expected, output);
    }

    @Test
    public void getDateTest() {
        Calendar output = meeting.getDate();
        Calendar expected = new GregorianCalendar(2017, 01, 10);
        assertEquals(expected, output);
    }

    @Test
    public void getContactsTest() {
        Set<Contact> output = meeting.getContacts();
        Set<Contact> expected = contacts;
        assertEquals(expected, output);
    }

}