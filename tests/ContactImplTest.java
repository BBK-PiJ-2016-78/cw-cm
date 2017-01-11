import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hradev01 on 09-Jan-17.
 */
class ContactImplTest {

    Contact contacts = new ContactImpl(1, "name", "");

    @Test
    void getIdTest() {
        int output = contacts.getId();
        int expected = 1;
        assertEquals(expected, output);
    }

    @Test
    void TestBorders() {
        try {
            Contact border = new ContactImpl(0, null);
            fail("Expected IllegalArgumentException or NullPointerException");
        } catch (IllegalArgumentException | NullPointerException ex) {
            // if caught test Pass
        }

    }

    @Test
    void getNameTest() {
        String output = contacts.getName();
        String expected = "name";
        assertEquals(expected, output);
    }

    @Test
    void getNotesTest() {
        String output = contacts.getNotes();
        String expected = "";
        assertEquals(expected, output);
    }

   /* @Test
    void addNotes() {

    }*/

}