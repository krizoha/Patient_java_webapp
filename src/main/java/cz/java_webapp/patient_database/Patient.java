package cz.java_webapp.patient_database;

import java.util.*;

public class Patient {
    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String sex;
    private String occupation;
    private String maritalStatus;
    private String disease;
    private String familyHistory;
    private Date dateOfLastMedicalCheck;

    public Long getId() {
        return id;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newValue) {
        title = newValue;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newValue) {
        firstName = newValue;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newValue) {
        lastName = newValue;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date newValue) {
        dateOfBirth = newValue;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String newValue) {
        maritalStatus = newValue;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String newValue) {
        familyHistory = newValue;
    }

    public Date getDateOfLastMedicalCheck() {
        return dateOfLastMedicalCheck;
    }

    public void setDateOfLastMedicalCheck(Date newValue) {
        dateOfLastMedicalCheck = newValue;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String newValue) {
        sex = newValue;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String newValue) {
        occupation = newValue;
    }


    public String getDisease() {
        return disease;
    }

    public void setDisease(String newValue) {
        disease = newValue;
    }

    public Patient(){

    }

    public Patient(Long id, String title, String firstName, String lastName, Date dateOfBirth, String sex, String occupation, String maritalStatus, String disease, String familyHistory, Date dateOfLastMedicalCheck) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.disease = disease;
        this.familyHistory = familyHistory;
        this.dateOfLastMedicalCheck = dateOfLastMedicalCheck;
    }
}
