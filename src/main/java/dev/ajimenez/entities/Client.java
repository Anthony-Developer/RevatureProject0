package dev.ajimenez.entities;

public class Client {

    private int clientID;
    private String firstName;
    private String lastName;
    private String address;
    private String cityState;
    private String dob;
    private int creditScore;

    public Client() {

    }

    public Client(int clientID,String firstname, String lastName, String address, String cityState, String dob, int creditScore) {
        this.clientID = clientID;
        this.firstName = firstname;
        this.lastName = lastName;
        this.address = address;
        this.cityState = cityState;
        this.dob = dob;
        this.creditScore = creditScore;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public String toString() {
        return " Client {" +
                "clientID=" + clientID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", cityState='" + cityState + '\'' +
                ", dob='" + dob + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }
}
