package exam.model;

import java.io.Serializable;

public class Patient implements Serializable {
    private int id;
    private String firstName;
    private String surName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int medCardNumber;
    private String diagnosis;
    public Patient(int id, String firstName, String surName, String lastName, String address,
                   String phoneNumber, int medCardNumber, String diagnosis) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.medCardNumber = medCardNumber;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMedCardNumber() {
        return medCardNumber;
    }
    public void setMedCardNumber(int medCardNumber) {
        this.medCardNumber = medCardNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "ID = " + id + "\nFirstName = "+ firstName + "\nSurName = " + surName
                + "\nLastName = " + lastName + "\nAddress = " + address + "\nPhoneNumber = "
                + phoneNumber + "\nNumber of medical card = " + medCardNumber + "\nDiagnosis = "
                + diagnosis;
    }
}
