import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public class PastMeetingImpl{

    private int id;
    private Calendar date;
    private Set<Contact> contacts;
    private String notes = "";

    /**
     *
     * @param id
     * @param date
     * @param contacts
     * @param notes
     * @throws IllegalArgumentException when id <=0 or contacts is empty
     * @throws NullPointerException when either date or contacts are null
     */
    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes)
            throws IllegalArgumentException, NullPointerException {
        this.id = id;
        this.date = date;
        this.contacts = contacts;
        this.notes = notes;
        if (date == null || contacts == null || notes == null)
            throw new NullPointerException("date or contact set are null!");
        if (id <= 0 || contacts.size() == 0)
            throw new IllegalArgumentException("Either id <=0 or the contacts set is empty!");
    }

    public String getNotes(){
        return notes;
    }
}
