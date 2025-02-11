///////////////////////////////
//Name: Astrid French        //
//CS 320, Project One        //
//Professor 0mar Toledo      //
//8 April 2024               //
//////////////////////////////

public class Contact {
    private String contactID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    // Constructor
    public Contact(String contactID, String firstName, String lastName,
                   String phoneNumber, String address){
        //if contactID is null and length more than 10 chars
        if(contactID == null || contactID.length()>10){
            throw new IllegalArgumentException("Invalid id");
        }
        this.contactID = contactID;
        //if firstName is null and length more than 10 chars
        if(firstName == null || firstName.length()>10){
            throw new IllegalArgumentException("Invalid first name.");
        }
        this.firstName = firstName;
        //if lastName is null and length more than 10 chars
        if(lastName == null || lastName.length()>10){
            throw new IllegalArgumentException("Invalid last name.");
        }
        this.lastName = lastName;
        //phoneNumber have to be a digit and 10 digits
        if(phoneNumber == null || phoneNumber.length() != 10 || !isValidPhoneNumber(phoneNumber)){
            throw new IllegalArgumentException("Invalid phone Number.");
        }
        this.phoneNumber = phoneNumber;
        //if address is null and length more than 30 chars
        if(address == null || address.length()>30){
            throw new IllegalArgumentException("Invalid address.");
        }
        this.address = address;
    }

    // Setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        // To accept that phone number have 10 digits and is a numeric
        if(phoneNumber == null || phoneNumber.length() != 10 || !isValidPhoneNumber(phoneNumber)){
            throw new IllegalArgumentException("Invalid phone Number.");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //Getter
    public String getContactID(){
        return contactID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getAddress(){
        return address;
    }

    // Method to check the phone number only of numeric digits
    private boolean isValidPhoneNumber(String str){
        //if the phone number is null and the digits is not 10
        if (str == null || str.length() != 10) {
            //return false
            return false;
        }
        //if the phone number is not null and the digit is 10
        for (char c : str.toCharArray()) {
            //to check if the character is not numeric(number)
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}