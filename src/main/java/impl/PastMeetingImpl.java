package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import spec.Contact;
import spec.PastMeeting;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable {

  private String notes;

  /**
   * @param id the id of the meeting.
   * @param date the date of the meeting.
   * @param contacts the contacts in th meeting.
   * @param notes the notes for the meeting.
   * @throws NullPointerException if the notes are null.
   */
  public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes)
                          throws NullPointerException {
    super(id, date, contacts);
    this.notes = notes;
    if (notes == null) {
      throw new NullPointerException("No notes provided");
    }
  }

  /**
   * @return the notes from the meeting.
   */
  public String getNotes() {
    return notes;
  }

}
