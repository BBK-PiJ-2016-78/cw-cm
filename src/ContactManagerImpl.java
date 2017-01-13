/**
 * Created by hradev01 on 09-Jan-17.
 */


import java.util.*;

public class ContactManagerImpl implements ContactManager {

    private int id = 0;
    private List<Meeting> futureMeetingList = new ArrayList<>();
    private List<Meeting> pastMeetingList = new ArrayList<>();
    private Set<Contact> contactSet = new HashSet<>();

    public ContactManagerImpl() {}

    public int addFutureMeeting(Set<Contact> contacts, Calendar date)
            throws IllegalArgumentException, NullPointerException {

        if(date != null && futureMeetingList != null) {
            if(!date.before(Calendar.getInstance()) && !contacts.contains(null)) {
                int size = futureMeetingList.size();
                if(size == 0) {
                    id++;
                } else if(futureMeetingList.get(size - 1).getId() % 2 == 0) { // IDs for futureMeetings are odd
                    id = futureMeetingList.get(size - 1).getId();
                    id++;
                } else {
                    id = futureMeetingList.get(size - 1).getId();
                    id += 2;
                }
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
                int size = pastMeetingList.size();
                if(size == 0) {
                    id = 2;
                } else if(pastMeetingList.get(size - 1).getId() % 2 == 1) { // IDs for pastMeetings are even
                    id = pastMeetingList.get(size - 1).getId();
                    id++;
                } else {
                    id = pastMeetingList.get(size - 1).getId();
                    id += 2;
                }
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

    public int addNewContact(String name, String notes)
            throws IllegalArgumentException, NullPointerException {

        if(name != null && notes != null) {
            if(name.length() != 0 && notes.length() != 0) {
                id = contactSet.size();
                id++;
                Contact contact = new ContactImpl(id, name, notes);
                contactSet.add(contact);
            } else {
                throw new IllegalArgumentException("Name or notes are empty!");
            }

        } else {
            throw new NullPointerException("Name or notes are null!");
        }
        return id;
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
