import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class MeetingImpl extends MeetingAbstract implements Serializable {

    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    /**
     *
     * @param id
     * @param date
     * @param contacts
     */
    public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
        this.id = id;
        this.date = date;
        this.contacts = contacts;
    }

    /**
     *
     * @return the id of the meeting
     */
    @Override
    public int getId(){
        return id;
    }

    /**
     *
     * @return the date of the meeting
     */
    @Override
    public Calendar getDate(){
        return date;
    }

    /**
     *
     * @return the details of people that attended the meeting
     */
    @Override
    public Set<Contact> getContacts(){
        return contacts;
    }

}
