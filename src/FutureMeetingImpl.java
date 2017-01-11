import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
    }
}
