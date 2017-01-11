import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class MeetingImpl extends MeetingAbstract {

    public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
    }
    @Override
    public int getId(){
        return 0;
    }

    @Override
    public Calendar getDate(){
        return null;
    }

    @Override
    public Set<Contact> getContacts(){
        return null;
    }
}
