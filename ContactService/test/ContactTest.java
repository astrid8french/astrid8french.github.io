///////////////////////////////
//Name: Astrid French        //
//CS 320, Project One        //
//Professor 0mar Toledo      //
//8 April 2024               //
//////////////////////////////


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void testContactClassSuccess() {
        Contact contact = new Contact("AstridF", "Astrid",
                "French", "5208692224", "9361 N Moon View");
        assertTrue(contact.getContactID().equals("AstridF"));
        assertTrue(contact.getFirstName().equals("Astrid"));
        assertTrue(contact.getLastName().equals("French"));
        assertTrue(contact.getPhoneNumber().equals("5208692224"));
        assertTrue(contact.getAddress().equals("9361 N Moon View"));
    }


    @Test
    void testContactIDTooLongFailed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("AstridFrench", "Astrid", "French",
                    "5208692224", "9361 N Moon View");
        });
    }

    @Test
    void testContactIdNullFailed(){
        Assertions.assertThrows(IllegalArgumentException.class,() ->{
            new Contact(null, "Astrid","French",
                    "5208692224","9361 N Moon View");
        });
    }

    @Test
    void testFirstNameisNullFailed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            new Contact("AstridF", null,
                    "French", "5208692224", "9361 N Moon View");
        });
    }

    @Test
    void testFirstNameisTooLongFailed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Contact("AstridF","Astrid12345",
                    "French","5208692224","9361 N Moon View");
        });
    }

    @Test
    void testLastNameisNullFailed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Contact("AstridF","Astrid",
                null,"5208692224","9361 N Moon View");
        });
    }

    @Test
    void testLastNameisTooLongFailed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Contact("AstridF","Astrid",
                    "French12345","5208692224","9361 N Moon View");
        });
    }

    @Test
    void testPhoneNumberisNullFailed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Contact("AstridF","Astrid",
                    "French",null,"9361 N Moon View");
        });
    }

    @Test
    void testPhoneNumberisNot10DigitsFailed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Contact("AstridF","Astrid",
                    "French","520869","9361 N Moon View");
        });
    }

    @Test
    void testPhoneNumberIsNotDigitFailed(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Contact("AstridF", "Astrid",
                    "French","SNHUUniver","9361 N Moon View");
        });
    }

    @Test
    void testAddressisNullFailed(){
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            new Contact("AstridF","Astrid12345",
                    "French","5208692224",null);
        });
    }

    @Test
    void testAddressisTooLongFailed(){
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            new Contact("AstridF","Astrid12345",
                    "French","5208692224","9361 N Moon View MoonViewMoonViewMoon");
        });
    }

}
















