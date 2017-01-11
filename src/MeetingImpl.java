import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class MeetingImpl extends MeetingAbstract {

    /**
     *
     * @param id
     * @param date
     * @param contacts
     */
    public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
    }

    /**
     *
     * @return the id of the meeting
     */
    @Override
    public int getId(){
        return 0;
    }

    /**
     *
     * @return the date of the meeting
     */
    @Override
    public Calendar getDate(){
        return null;
    }

    /**
     *
     * @return the details of people that attended the meeting
     */
    @Override
    public Set<Contact> getContacts(){
        return null;
    }
}
