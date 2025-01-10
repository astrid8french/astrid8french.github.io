///////////////////////////////
//Name: Astrid French        //
//CS 320, Project One        //
//Professor 0mar Toledo      //
//8 April 2024               //
//////////////////////////////


import java.util.ArrayList;

public class ContactService {

    ArrayList<Contact> list = new ArrayList<Contact>();

    public boolean addContact(Contact contact) {
        boolean didAdd = false;

        //if the list is empty, it will add the contact to the list
        if (list.size() == 0) {
            list.add(contact);
            //if the contact add successfully
            didAdd = true;
        }
        //if the list is not empty
        else {
            //iterate the list to see if the new contact matches with any existing contact
            for (Contact s : list) {
                if (contact.getContactID().equalsIgnoreCase(s.getContactID())) {
                    return didAdd;
                }
            }
            //if no list with the same contact, add the contact
            list.add(contact);
            didAdd = true;
        }

            return didAdd;
        }
    public boolean deleteContact(String contactID) {
        boolean didDelete = false;

        // Iterate through each contact in the list
        for (int i = 0; i < list.size(); i++) {
            // Get the contact at the current index
            Contact currentContact = list.get(i);
            // Check if the ID of the current contact matches the specified contactID
            if (currentContact.getContactID().equalsIgnoreCase(contactID)) {
                // remove the contact from the list
                list.remove(i);
                didDelete = true;
                // to adjust the removal of the contact
                i--;
            }
        }

        // Return the contact was deleted successfully
        return didDelete;
    }

    //To update the first name by the contact ID
    public boolean updateContactFirstName(String contactId, String newFirstName) {
        for (Contact contact : list) {
            if (contact.getContactID().equalsIgnoreCase(contactId)) {
                contact.setFirstName(newFirstName);
                return true;
            }
        }
        return false;
    }

    //To update the last name by the contact ID
    public boolean updateContactLastName(String contactId, String newLastName) {
        for (Contact contact : list) {
            if (contact.getContactID().equalsIgnoreCase(contactId)) {
                contact.setLastName(newLastName);
                return true;
            }
        }
        return false;
    }

    //To update the phone number by the contact ID
    public boolean updateContactPhone(String contactId, String newPhoneNumber) {
        for (Contact contact : list) {
            if (contact.getContactID().equalsIgnoreCase(contactId)) {
                contact.setPhoneNumber(newPhoneNumber);
                return true;
            }
        }
        return false;
    }

    //To update the address by the contact ID
    public boolean updateContactAddress(String contactId, String newAddress) {
        for (Contact contact : list) {
            if (contact.getContactID().equalsIgnoreCase(contactId)) {
                contact.setAddress(newAddress);
                return true;
            }
        }
        return false;


    }
        }
