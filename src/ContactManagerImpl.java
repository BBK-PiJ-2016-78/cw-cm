/**
 * Created by hradev01 on 09-Jan-17.
 */

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl {

    int addFutureMeeting(Set<Contact> contacts, Calendar date){
        return 0;
    }

    PastMeeting getPastMeeting(int id){
        return null;
    }

    FutureMeeting getFutureMeeting(int id){
        return null;
    }

    List<Meeting> getFutureMeetingList(Contact contact){
        return null;
    }

    List<Meeting> getMeetingListOn(Calendar date){
        return null;
    }

    List<PastMeeting> getPastMeetingListFor(Contact contact){
        return null;
    }

    int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
        return 0;
    }

    PastMeeting addMeetingNotes(int id, String text){
        return null;
    }

    int addNewContact(String name, String notes){
        return 0;
    }

    Set<Contact> getContacts(String name){
        return null;
    }

    Set<Contact> getContacts(int... ids){
        return null;
    }

    void flush(){

    }
}
