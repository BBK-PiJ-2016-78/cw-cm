import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hradev01 on 09-Jan-17.
 */
class ContactImplTest {

    Contact contacts = new ContactImpl();

    @Test
    void getId() {
        int output = contacts.getId();
        int expected = 1;
        assertEquals(expected, output);
    }

    @Test
    void getName() {
        String output = contacts.getName();
        String expected = "name";
        assertEquals(expected, output);
    }

    @Test
    void getNotes() {
        String output = contacts.getNotes();
        String expected = "";
        assertEquals(expected, output);
    }

   /* @Test
    void addNotes() {

    }*/

}