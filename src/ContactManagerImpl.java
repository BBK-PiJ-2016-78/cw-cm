/**
 * Created by hradev01 on 09-Jan-17.
 */


import java.util.*;

public class ContactManagerImpl implements ContactManager {

    private int id = 0;
    private List<Meeting> futureMeetingList = new ArrayList<>();
    private List<Meeting> pastMeetingList = new ArrayList<>();

    public ContactManagerImpl() {}

    public int addFutureMeeting(Set<Contact> contacts, Calendar date)
            throws IllegalArgumentException, NullPointerException {

        if(date != null && futureMeetingList != null) {
            if(!date.before(Calendar.getInstance()) && !contacts.contains(null)) {
                id = futureMeetingList.size();
                id++;
                Meeting futureMeeting = new MeetingImpl(id, date, contacts);
                futureMeetingList.add(futureMeeting);
            } else {
                throw new IllegalArgumentException("Added date is in the past or contact doesn't exist!");
            }
        } else {
            throw new NullPointerException("Meeting or date are null!");
        }

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

    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text)
            throws IllegalArgumentException, NullPointerException {

        if(date != null && contacts != null && text != null) {
            if(date.before(Calendar.getInstance()) && !contacts.contains(null) && contacts.size() != 0) {
                id = pastMeetingList.size();
                id++;
                Meeting pastMeeting = new PastMeetingImpl(id, date, contacts, text);
                pastMeetingList.add(pastMeeting);
            } else {
                throw new IllegalArgumentException("Added date is in the future or contact doesn't exist!");
            }
        } else {
            throw new NullPointerException("Meeting or date are null!");
        }

        return id;
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
