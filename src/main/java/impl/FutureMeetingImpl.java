package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import spec.Contact;
import spec.FutureMeeting;

/**
 * Created by hradev01 on 11-Jan-17.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {

  /**
   * @param id       the id of the meeting.
   * @param date     the date of the meeting.
   * @param contacts the contacts participating in the meeting.
   */
  public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
    super(id, date, contacts);
  }
}
