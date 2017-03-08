package impl;

import spec.Contact;
import spec.PastMeeting;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable {

    private String notes;

    /**
     *
     * @param id
     * @param date
     * @param contacts
     * @param notes
     * @throws NullPointerException
     */
    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes) throws NullPointerException {
        super(id, date, contacts);
        this.notes = notes;
        if (notes == null)
            throw new NullPointerException("No notes provided");
    }

    /**
     *
     * @return the notes from the meeting
     */
    public String getNotes(){
        return notes;
    }

}
