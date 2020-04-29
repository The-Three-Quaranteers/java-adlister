package models;

import java.util.List;

//Interface for our contact bean (to be implemented by our DAO)

public interface Contacts {
    // get a list of all the contacts
    List<Contact> getContacts();

    // method for saving a contact
    long saveContact(Contact contact);

    // method for deleting a contact
    void deleteContactById(long id);

    //method for getting a contact by ID
    Contact getContactById(long id);

//    // insert a new contact and returns the new ad's id
//    Long insert(models.Contact contact);
}