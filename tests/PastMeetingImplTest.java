import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hradev01 on 09-Jan-17.
 */
class PastMeetingImplTest {

    @Test
    void getNotesTest() {
        Calendar date = new GregorianCalendar(2017, 01, 10);
        Set<Contact> contacts = new HashSet<>();
        Contact contact = new ContactImpl(1, "name", "");
        contacts.add(contact);
        PastMeeting meet = (PastMeeting) new PastMeetingImpl(1, date, contacts, "");

        String output = meet.getNotes();
        String expected = "";
        assertEquals(expected, output);
    }

}