///////////////////////////////
//Name: Astrid French        //
//CS 320, Project One        //
//Professor 0mar Toledo      //
//8 April 2024               //
//////////////////////////////


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    @Test
    void testAddContactDuplicateIdFail() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        Contact contact2 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
        assertFalse(contactService.addContact(contact2));
    }

    @Test
    void testAddContactSuccess() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
    }

    @Test
    void testDeleteContactSuccess() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
        assertTrue(contactService.deleteContact("AstridF"));
    }

    @Test
    void testUpdateContactFirstNameSuccess() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
        assertTrue(contactService.updateContact("AstridF", "firstname", "Paul"));
    }

    @Test
    void testUpdateContactLastNameSuccess() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
        assertTrue(contactService.updateContact("AstridF", "lastname", "SNHU"));
    }

    @Test
    void testUpdatePhoneNumberSuccess() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
        assertTrue(contactService.updateContact("AstridF", "phone", "1111111111"));
    }

    @Test
    void testUpdateAddressSuccess() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
        assertTrue(contactService.updateContact("AstridF", "address", "8888 South Ln Carolina"));
    }

    @Test
    void testUpdateNonexistentContactFail() {
        ContactService contactService = new ContactService();
        assertFalse(contactService.updateContact("NonExistentID", "firstname", "John"));
    }

    @Test
    void testUpdateInvalidFieldFail() {
        ContactService contactService = new ContactService();
        Contact contact1 = new Contact("AstridF", "Astrid", "French", "5208692224", "9361 N Moon View");
        assertTrue(contactService.addContact(contact1));
        assertFalse(contactService.updateContact("AstridF", "invalidField", "NewValue"));
    }
}


