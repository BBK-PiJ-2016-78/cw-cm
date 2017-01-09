import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public abstract class MeetingImpl implements Meeting {


    public abstract int getId();

    public abstract Calendar getDate();

    public abstract Set<Contact> getContacts();
}
