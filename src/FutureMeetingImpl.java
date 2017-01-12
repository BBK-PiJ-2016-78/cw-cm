import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    /**
     *
     * @param id
     * @param date
     * @param contacts
     */
    public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
        this.id = id;
        this.date = date;
        this.contacts = contacts;
    }
}
