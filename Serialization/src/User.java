public class User implements java.io.Serializable {
    private String firstName;
    private String lastName;
    private transient int personalNumber;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }
    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }
}