package models;

import java.util.ArrayList;
import java.util.List;

public class ContactsListDao implements Contacts{
    //we need a list variable to hold all the contacts
    private List<Contact> contacts = new ArrayList<>();



    // get a list of all the contacts
    @Override
    public List<Contact> getContacts(){
        return contacts;
    };

    // method for saving a contact
    @Override
    public long saveContact(Contact contact){
        // check to see if this contact doesnt have an id, generate id and set for contact.
        if (contact.getId() == 0) {
            contact.setId(contacts.size() + 1);
            contacts.add(contact);
        } else {
            //already have the correct id from sql table
            contacts.set((int) contact.getId(), contact);
        }
        //return id of newly made contact
        return contact.getId();
    };

    // method for deleting a contact
    @Override
    public void deleteContactById(long id){
        contacts.remove((int) id - 1);
    };

    //method for getting a contact by ID
    @Override
    public Contact getContactById(long id){
        return contacts.get((int) id - 1);
    };


    public static void main(String[] args) {
        Contacts contactDao = new ContactsListDao();

        //test the getContacts method
        System.out.println("\n=== Testing getContacts()");
        List<Contact> allContacts = contactDao.getContacts();

        for (Contact contact: allContacts){
            System.out.println(contact.getFirstName());
        }
    }
}
