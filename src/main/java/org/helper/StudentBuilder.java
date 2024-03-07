package org.helper;

import org.entity.Student;

public class StudentBuilder {
    //TODO: import lombok
    private String firstName;
    private String lastName;
    private String email;
    private int gender;
    private String mobileNumber;
    private String dateOfBirth;
    private String subject;
    private int hobby;
    private String pictureName;
    private String currentAddress;
    private String state;
    private String city;
    private String stateAndCity;

    public StudentBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder email(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder gender(int gender) {
        this.gender = gender;
        return this;
    }

    public StudentBuilder mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public StudentBuilder dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public StudentBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public StudentBuilder hobby(int hobby) {
        this.hobby = hobby;
        return this;
    }

    public StudentBuilder pictureName(String pictureName) {
        this.pictureName = pictureName;
        return this;
    }

    public StudentBuilder currentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
        return this;
    }

    public StudentBuilder state(String state) {
        this.state = state;
        return this;
    }

    public StudentBuilder city(String city) {
        this.city = city;
        return this;
    }

    public StudentBuilder stateAndCity(String stateAndCity) {
        this.stateAndCity = stateAndCity;
        return this;
    }

    public Student build() {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setGender(gender);
        student.setMobileNumber(mobileNumber);
        student.setDateOfBirth(dateOfBirth);
        student.setSubject(subject);
        student.setHobby(hobby);
        student.setPictureName(pictureName);
        student.setCurrentAddress(currentAddress);
        student.setState(state);
        student.setCity(city);
        student.setStateAndCity(stateAndCity);
        return student;
    }
}
