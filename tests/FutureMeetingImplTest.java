import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by hradev01 on 12-Jan-17.
 */
public class FutureMeetingImplTest {

    Calendar date = new GregorianCalendar(2017, 01, 10);
    Set<Contact> contacts = new HashSet<>();
    Contact contact = new ContactImpl(1, "name", "stuff");

    @Test
    public void constructorTest() {
        try {
            FutureMeeting meet = new FutureMeetingImpl(1, date, contacts);
            fail("Expected IllegalArgumentException or NullPointerException");
        } catch (IllegalArgumentException | NullPointerException ex) {
            // if caught test Pass
        }
    }
}