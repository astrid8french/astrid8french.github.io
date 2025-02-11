///////////////////////////////
//Name: Astrid French        //
//CS 320, Project One        //
//Professor 0mar Toledo      //
//8 April 2024               //
//////////////////////////////


import java.util.HashMap;

public class ContactService {
    //Store contact using hashMap
    private HashMap<String, Contact> contactMap = new HashMap<>();

    //Add Contact method
    public boolean addContact(Contact contact) {
        if (!contactMap.containsKey(contact.getContactID())) {
            contactMap.put(contact.getContactID(), contact);
            return true;
        }
        return false;
    }

    //Delete Contact Method
    public boolean deleteContact(String contactID) {
        return contactMap.remove(contactID) != null;
    }

    //Update Contact Method
    public boolean updateContact(String contactId, String field, String newValue) {
        Contact contact = contactMap.get(contactId);
        if (contact != null) {
            switch (field.toLowerCase()) {
                case "firstname":
                    contact.setFirstName(newValue);
                    break;
                case "lastname":
                    contact.setLastName(newValue);
                    break;
                case "phone":
                    contact.setPhoneNumber(newValue);
                    break;
                case "address":
                    contact.setAddress(newValue);
                    break;
                default:
                    return false;
            }
            return true;
        }
        //If contact not found
        return false;
    }
}
