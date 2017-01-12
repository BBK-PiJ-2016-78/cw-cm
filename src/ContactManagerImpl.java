/**
 * Created by hradev01 on 09-Jan-17.
 */


import java.util.*;

public class ContactManagerImpl implements ContactManager {

    private int id = 0;
    private List<Meeting> futureMeetings = new ArrayList<>();

    public ContactManagerImpl() {}

    public int addFutureMeeting(Set<Contact> contacts, Calendar date)
            throws IllegalArgumentException, NullPointerException {

        if(date != null && futureMeetings != null) {
            if(!date.before(Calendar.getInstance()) && !contacts.contains(null)) {
                id = futureMeetings.size();
                id++;
                Meeting futureMeeting = new MeetingImpl(id, date, contacts);
                futureMeetings.add(futureMeeting);
            } else {
                throw new IllegalArgumentException("Added date is in the past or contact doesn't exist!");
            }
        } else {
            throw new NullPointerException("Meeting or date are null!");
        }
        if (id <= 0)
            throw new IllegalArgumentException("Meeting failed to be added!");

        return id;
    }

    public PastMeeting getPastMeeting(int id){
        return null;
    }

    public FutureMeeting getFutureMeeting(int id){
        return null;
    }

    public Meeting getMeeting(int id){
        return null;
    }

    public List<Meeting> getFutureMeetingList(Contact contact){
        return null;
    }

    public List<Meeting> getMeetingListOn(Calendar date){
        return null;
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact){
        return null;
    }

    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
        return 0;
    }

    public PastMeeting addMeetingNotes(int id, String text){
        return null;
    }

    public int addNewContact(String name, String notes){
        return 0;
    }

    public Set<Contact> getContacts(String name){
        return null;
    }

    public Set<Contact> getContacts(int... ids){
        return null;
    }

    public void flush(){

    }
}
