package impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import spec.Contact;
import spec.Meeting;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public abstract class MeetingAbstract implements Meeting, Serializable {

  private int id;
  private Calendar date;
  private Set<Contact> contacts;

  /**
   * @param id the id of the meeting.
   * @param date the date the meeting took place.
   * @param contacts the contacts participating in the meeting.
   * @throws IllegalArgumentException when id <=0 or contacts is empty.
   * @throws NullPointerException     when either date or contacts are null.
   */
  public MeetingAbstract(int id, Calendar date, Set<Contact> contacts)
      throws IllegalArgumentException, NullPointerException {
    this.id = id;
    this.date = date;
    this.contacts = contacts;

    if (date == null || contacts == null) {
      throw new NullPointerException("date or contact set are null!");
    }
    if (id <= 0 || contacts.size() == 0) {
      throw new IllegalArgumentException("Either id <=0 or the contacts set is empty!");
    }
  }

  public abstract int getId();

  public abstract Calendar getDate();

  public abstract Set<Contact> getContacts();
}
