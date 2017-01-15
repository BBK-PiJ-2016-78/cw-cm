/**
 * Created by hradev01 on 09-Jan-17.
 */


import java.util.*;
import java.util.stream.Collectors;

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
                Meeting futureMeeting = new FutureMeetingImpl(id, date, contacts);
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

        for(Meeting count : pastMeetingList) {
            if (count.getId() == id)
                return (PastMeeting) count;
        }

        for(Meeting count : futureMeetingList) {
            if (count.getId() == id && !count.getDate().before(Calendar.getInstance()))
                throw new IllegalStateException("This is a future planned meeting!");
            else {
                pastMeetingList.add(count);
                return (PastMeeting) count;
            }
        }
        return null;
    }

    public FutureMeeting getFutureMeeting(int id){

        for(Meeting count : futureMeetingList) {
            if(count.getId() == id)
                return (FutureMeeting) count;
        }

        for(Meeting count : pastMeetingList) {
            if(count.getId() == id)
                throw new IllegalStateException("This is a past meeting!");
        }

        return null;
    }

    public Meeting getMeeting(int id){

        if(id > 0) {
            for (Meeting count : futureMeetingList) {
                if(count.getId() == id)
                    return count;
            }
            for (Meeting count: pastMeetingList) {
                if(count.getId() == id)
                    return count;
            }
        }
        return null;
    }

    public List<Meeting> getFutureMeetingList(Contact contact){

        if(contact == null) {
            throw new NullPointerException("The contact does not exist!");
        //} else if() {

        } else {
            List<Meeting> streamList = futureMeetingList.stream()
                    .distinct()
                    .filter(a -> a.getContacts().contains(contact))
                    .sorted(Comparator.comparing(Meeting::getDate))
                    .collect(Collectors.toList());

            return streamList;
        }
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

    public PastMeeting addMeetingNotes(int id, String text) {

        for(Meeting count : futureMeetingList) {
            if(count.getDate().before(Calendar.getInstance())) { // If any of the future meetings already occurred add it to past list
                pastMeetingList.add(count);
            }
        }
        PastMeeting past = getPastMeeting(id);
        PastMeeting addedNotes;

        if(past == null)
            throw new IllegalArgumentException("Meeting does not exist!");
        else {
            int index = pastMeetingList.indexOf(past);
            int newId = past.getId();
            Calendar newDate = past.getDate();
            Set<Contact> newContacts = past.getContacts();
            if(text == null)
                throw new NullPointerException("No notes added!");
            else {
                pastMeetingList.remove(index);
                addedNotes = new PastMeetingImpl(newId, newDate, newContacts, text);
                pastMeetingList.add(index, addedNotes);
                return addedNotes;
            }
        }
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

        Set<Contact> newSet = new HashSet<>();

        if(name == null) {
            throw new NullPointerException("No name specified");
        } else if(name.equals("")) {
            return contactSet;
        } else {
            for (Contact count : contactSet) {
                if (count.getName().equals(name)) {
                    newSet.add(count);
                }
            }
        }
        return newSet;
    }

    public Set<Contact> getContacts(int... ids){

        Set<Contact> newSet = new HashSet<>();

        if(id == 0)
            throw new IllegalArgumentException("ID can't be 0");
        else {
            for(Contact count : contactSet) {
                for(int id : ids) {
                    if (count.getId() == id)
                        newSet.add(count);
                }
            }
        }
        if(newSet.size() == 0)
            throw new IllegalArgumentException("No contacts found with this ID!");

        return newSet;
    }

    public void flush(){

    }
}
