package impl;

import spec.Contact;

import java.io.Serializable;

/**
 * Created by hradev01 on 09-Jan-17.
 */
public class ContactImpl implements Contact, Serializable {

    private int id;
    private String name;
    private String notes = "";

    /**
     *
     * @param id the ID of the contact
     * @param name the name of the contact
     * @param notes the notes to be added
     */
    public ContactImpl(int id, String name, String notes){
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    /**
     *
     * @param id the ID of the contact
     * @param name the name of the contact
     * @throws IllegalArgumentException when the id argument is 0 or negative
     * @throws NullPointerException when the passed name argument is a null
     */
    public ContactImpl(int id, String name) throws IllegalArgumentException, NullPointerException {
        this.id = id;
        if (id <= 0)
            throw new IllegalArgumentException("The ID is 0 or e negative value!");
        this.name = name;
        if (name == null)
            throw new NullPointerException("The name value is null!");
    }

    /**
     *
     * @return the ID of the contact
     */
    public int getId(){
        return id;
    }

    /**
     *
     * @return the name of the contact
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return a string with notes about the contact, if none, return empty string
     */
    public String getNotes(){
        return notes;
    }

    /**
     *
     * @param note the notes to be added
     */
    public void addNotes(String note){
        notes = note;
    }
}
