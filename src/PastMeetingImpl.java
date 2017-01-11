import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String notes = "";

    /**
     *
     * @param id
     * @param date
     * @param contacts
     */
    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
    }

    /**
     *
     * @return the notes from the meeting
     */
    public String getNotes(){
        return notes;
    }

}
