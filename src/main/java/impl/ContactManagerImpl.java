package impl;

/*
  Created by hradev01 on 09-Jan-17.
 */

import spec.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ContactManagerImpl implements ContactManager, Serializable {

    private int id = 0;
    private List<Meeting> futureMeetingList = new ArrayList<>();
    private List<Meeting> pastMeetingList = new ArrayList<>();
    private Set<Contact> contactSet = new HashSet<>();


    /**
     *
     * @param contacts a set of contacts that will participate in the meeting
     * @param date the date on which the meeting will take place
     * @return the ID, odd values for future meetings
     * @throws IllegalArgumentException if meeting is in the past or contact doesn't exist
     * @throws NullPointerException if meeting or date are null
     */
    public int addFutureMeeting(Set<Contact> contacts, Calendar date)
            throws IllegalArgumentException, NullPointerException {

        if (date != null && futureMeetingList != null) {
            if (!date.before(Calendar.getInstance()) && !contacts.contains(null)) {
                int size = futureMeetingList.size();
                if (size == 0) {
                    id++;
                } else if (futureMeetingList.get(size - 1).getId() % 2 == 0) { // IDs for futureMeetings are odd
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


    /**
     *
     * @param id the ID for the meeting
     * @return PastMeeting with requested ID
     * @throws IllegalStateException if the meeting with this ID is happening in future
     */
    public PastMeeting getPastMeeting(int id) throws IllegalStateException {

        for (Meeting count : pastMeetingList) {
            if (count.getId() == id) {
                return (PastMeeting) count;
            }
        }

        for (Meeting count : futureMeetingList) {
            if (count.getId() == id && !count.getDate().before(Calendar.getInstance())) {
                throw new IllegalStateException("This is a future planned meeting!");
            } else {
                pastMeetingList.add(count);
                return (PastMeeting) count;
            }
        }
        return null;
    }


    /**
     *
     * @param id the ID for the meeting
     * @return the FutureMeeting for this ID
     * @throws IllegalStateException if the meeting is happening in the past
     */
    public FutureMeeting getFutureMeeting(int id) throws IllegalStateException {

        for (Meeting count : futureMeetingList) {
            if(count.getId() == id)
                return (FutureMeeting) count;
        }

        for (Meeting count : pastMeetingList) {
            if (count.getId() == id) {
                throw new IllegalStateException("This is a past meeting!");
            }
        }

        return null;
    }


    /**
     *
     * @param id the ID for the meeting
     * @return the Meeting with the requested ID
     */
    public Meeting getMeeting(int id) {

        if (id > 0) {
            for (Meeting count : futureMeetingList) {
                if (count.getId() == id) {
                    return count;
                }
            }
            for (Meeting count: pastMeetingList) {
                if (count.getId() == id) {
                    return count;
                }
            }
        }
        return null;
    }


    /**
     *
     * @param contact one of the user’s contacts
     * @return the list of FutureMeetings with that contact
     * @throws NullPointerException if contact is null
     * @throws IllegalArgumentException if contact does not exist
     */
    public List<Meeting> getFutureMeetingList(Contact contact)
            throws NullPointerException, IllegalArgumentException {

        boolean check = false;

        if (contact == null) {
            throw new NullPointerException("The contact does not exist!");
        }

        List<Contact> contactList = new ArrayList<>(contactSet);
        for (Contact count : contactList) {
            if (count.getId() == contact.getId() && count.getName().equals(contact.getName())) {
                check = true;
            }
        }
        if (!check)
            throw new IllegalArgumentException("Cannot find contact!");

            List<Meeting> streamList = futureMeetingList.stream()
                    .filter(a -> a.getContacts().contains(contact))
                    .sorted(Comparator.comparing(Meeting::getDate))
                    .collect(Collectors.toList());

            for (int i = 0; i < streamList.size() - 1; i++) {
                if (streamList.get(i).getDate().compareTo(streamList.get(i + 1).getDate()) == 0) // remove duplicates with same date
                    streamList.remove(i);
            }

            return streamList;
    }


    /**
     *
     * @param date the date
     * @return the list of Meetings on that date
     * @throws NullPointerException if the date is null
     */
    public List<Meeting> getMeetingListOn(Calendar date)
            throws NullPointerException {

        if (date == null) {
            throw new NullPointerException("Date is null!");
        }

        List<Meeting> allMeetings = new ArrayList<>();

        for (Meeting count : futureMeetingList) {
            allMeetings.add(count);
        }
        for (Meeting count : pastMeetingList) {
            allMeetings.add(count);
        }

        List<Meeting> streamList = allMeetings.stream().distinct()
                .filter(a -> a.getDate().equals(date))
                .sorted(Comparator.comparing(Meeting::getDate))
                .collect(Collectors.toList());

        for (int i = 0; i < streamList.size() - 1; i++) {
            if (streamList.get(i).getDate().compareTo(streamList.get(i + 1).getDate()) == 0
                    && streamList.get(i).getContacts().containsAll(contactSet)
                    && streamList.get(i + 1).getContacts().containsAll(contactSet)) { // remove duplicates with same date and contacts
                streamList.remove(i);
            }
        }

        return streamList;

    }


    /**
     *
     * @param contact one of the user’s contacts
     * @return the list of PastMeetings with that contact
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException if the contact is null
     */
    public List<PastMeeting> getPastMeetingListFor(Contact contact)
            throws IllegalArgumentException, NullPointerException {

        boolean check = false;

        if (contact == null) {
            throw new NullPointerException("The contact does not exist!");
        }

        List<Contact> contactList = new ArrayList<>(contactSet);
        for (Contact count : contactList) {
            if (count.getId() == contact.getId() && count.getName().equals(contact.getName())) {
                check = true;
            }
        }
        if (!check) {
            throw new IllegalArgumentException("Cannot find contact!");
        }

        List<PastMeeting> streamList = pastMeetingList.stream()
                .filter(a -> a.getContacts().contains(contact))
                .sorted(Comparator.comparing(Meeting::getDate))
                .map(PastMeeting.class::cast)
                .collect(Collectors.toList());

        for (int i = 0; i < streamList.size() - 1; i++) {
            if (streamList.get(i).getDate().compareTo(streamList.get(i + 1).getDate()) == 0) { // remove duplicates with same date
                streamList.remove(i);
            }
        }

        return streamList;
    }


    /**
     *
     * @param contacts a set of participants
     * @param date the date on which the meeting took place
     * @param text messages to be added about the meeting.
     * @return the ID of the past meeting, it returns even IDs
     * @throws IllegalArgumentException if the list of contacts is empty or contact does not exist or date in future
     * @throws NullPointerException if any of the arguments are null
     */
    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text)
            throws IllegalArgumentException, NullPointerException {

        if (date != null && contacts != null && text != null) {
            if (date.before(Calendar.getInstance()) && !contacts.contains(null) && contacts.size() != 0) {
                int size = pastMeetingList.size();
                if (size == 0) {
                    id = 2;
                } else if(pastMeetingList.get(size - 1).getId() % 2 != 0) { // IDs for pastMeetings are even
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


    /**
     *
     * @param id the ID of the meeting
     * @param text messages to be added about the meeting.
     * @return the PastMeeting with notes added
     * @throws IllegalArgumentException if name or notes are empty strings
     * @throws NullPointerException if name or notes are null
     */
    public PastMeeting addMeetingNotes(int id, String text)
            throws IllegalArgumentException, NullPointerException {

        for (Meeting count : futureMeetingList) {
            if (count.getDate().before(Calendar.getInstance())) { // If any of the future meetings already occurred add it to past list
                pastMeetingList.add(count);
            }
        }
        PastMeeting past = getPastMeeting(id);
        PastMeeting addedNotes;

        if (past == null) {
            throw new IllegalArgumentException("Meeting does not exist!");
        } else {
            int index = pastMeetingList.indexOf(past);
            int newId = past.getId();
            Calendar newDate = past.getDate();
            Set<Contact> newContacts = past.getContacts();
            if (text == null) {
                throw new NullPointerException("No notes added!");
            } else {
                pastMeetingList.remove(index);
                addedNotes = new PastMeetingImpl(newId, newDate, newContacts, text);
                pastMeetingList.add(index, addedNotes);
                return addedNotes;
            }
        }
    }


    /**
     *
     * @param name the name of the contact.
     * @param notes notes to be added about the contact.
     * @return the ID of that contact, integer numbers from 1 to n
     * @throws IllegalArgumentException if name or notes are empty strings
     * @throws NullPointerException if name or notes are null
     */
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


    /**
     *
     * @param name the string to search for
     * @return the set of contacts whose name contains that string
     * @throws NullPointerException if the name provided is null
     */
    public Set<Contact> getContacts(String name)
            throws NullPointerException {

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


    /**
     *
     * @param ids an arbitrary number of contact IDs
     * @return the set of contacts with the provided IDs
     * @throws IllegalArgumentException if no IDs provided or the don't correspond to any contacts
     */
    public Set<Contact> getContacts(int... ids)
            throws IllegalArgumentException {

        Set<Contact> newSet = new HashSet<>();

        if (id == 0) {
            throw new IllegalArgumentException("ID can't be 0");
        } else {
            for (Contact count : contactSet) {
                for (int id : ids) {
                    if (count.getId() == id) {
                        newSet.add(count);
                    }
                }
            }
        }
        if (newSet.size() == 0) {
            throw new IllegalArgumentException("No contacts found with this ID!");
        }

        return newSet;
    }


    /**
     * To be used to load all data from files, when the program is first launched and before writing new data
     */
    public void loadData() {

        String contactsLoad = "./src/main/java/impl/contactsData.ser";
        String futureMeetingsLoad = "./src/main/java/impl/futureMeetingsData.ser";
        String pastMeetingsLoad = "./src/main/java/impl/pastMeetingsData.ser";

        try (FileInputStream fis = new FileInputStream(contactsLoad)) { //Load Contacts data from files

            ObjectInputStream ois = new ObjectInputStream(fis);
            contactSet = (Set<Contact>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e)  {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(futureMeetingsLoad)) { // Load futureMeetings data from file

            ObjectInputStream ois = new ObjectInputStream(fis);
            futureMeetingList = (List<Meeting>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e)  {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(pastMeetingsLoad)) { // Load pastMeetings data from file

            ObjectInputStream ois = new ObjectInputStream(fis);
            pastMeetingList = (List<Meeting>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e)  {
            e.printStackTrace();
        }

        //after loading the list check if some of the dates in futureMeetingList are expired and remove them from list
        //add them to pastMeetingList with an even ID
        int newID;
        for (Meeting count : futureMeetingList) {
            if (count.getDate().compareTo(Calendar.getInstance()) == -1) {
                newID = pastMeetingList.size() + 2;
                pastMeetingList.add(new PastMeetingImpl(newID, count.getDate(), count.getContacts(), "past meeting"));
                futureMeetingList.remove(count);
            }
        }
    }


    /**
     * Used to write all current data to files, either when called or on shutdown
     */
    public void flush() {

        String contactsWrite = "./src/main/java/impl/contactsData.ser";
        String futureMeetingsWrite = "./src/main/java/impl/futureMeetingsData.ser";
        String pastMeetingsWrite = "./src/main/java/impl/pastMeetingsData.ser";

        try (FileOutputStream fos = new FileOutputStream(contactsWrite)) { // Write contacts data to file

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contactSet);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(futureMeetingsWrite)) { //Write futureMeetings data to file

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(futureMeetingList);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(pastMeetingsWrite)) { //Write pastMeetings data to file

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pastMeetingList);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Call the flush function in a new thread when shutting down
        try {
            Thread t = new Thread(this::flush, "Shutdown-thread");
            Runtime.getRuntime().addShutdownHook(t);
            Runtime.getRuntime().removeShutdownHook(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
