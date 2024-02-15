package org.helper;

import org.entity.Student;

public class StudentBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private int gender;
    private String mobileNumber;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private String subject;
    private int hobby;
    private String currentAddress;
    private String state;
    private String city;

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

    public StudentBuilder yearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    public StudentBuilder monthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
        return this;
    }

    public StudentBuilder dayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
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

    public Student build() {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setGender(gender);
        student.setMobileNumber(mobileNumber);
        student.setYearOfBirth(yearOfBirth);
        student.setMonthOfBirth(monthOfBirth);
        student.setDayOfBirth(dayOfBirth);
        student.setSubject(subject);
        student.setHobby(hobby);
        student.setCurrentAddress(currentAddress);
        student.setState(state);
        student.setCity(city);
        return student;
    }
}
