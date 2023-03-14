package CLI_Shop;

public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;

    public Customer(String firstName, String lastName, String phoneNumber, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    */

    public String getEmailAddress() {
        return emailAddress;
    }

    /*
    public void setPassword(String password) {
        this.password = password;
    }
    */

    public String getPassword() {
        return password;
    }
}
