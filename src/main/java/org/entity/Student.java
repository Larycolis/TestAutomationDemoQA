package org.entity;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private int gender;
    private String mobileNumber;
    private int yearOfBirth;
    private String monthOfBirth;
    private int dayOfBirth;
    private String subject;
    private int hobby;
    private String currentAddress;
    private String state;
    private String city;

    public Student() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getHobby() {
        return hobby;
    }

    public void setHobby(int hobby) {
        this.hobby = hobby;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (gender != student.gender) return false;
        if (yearOfBirth != student.yearOfBirth) return false;
        if (dayOfBirth != student.dayOfBirth) return false;
        if (hobby != student.hobby) return false;
        if (!firstName.equals(student.firstName)) return false;
        if (!lastName.equals(student.lastName)) return false;
        if (!Objects.equals(email, student.email)) return false;
        if (!Objects.equals(mobileNumber, student.mobileNumber))
            return false;
        if (!Objects.equals(monthOfBirth, student.monthOfBirth))
            return false;
        if (!Objects.equals(subject, student.subject)) return false;
        if (!Objects.equals(currentAddress, student.currentAddress))
            return false;
        if (!Objects.equals(state, student.state)) return false;
        return Objects.equals(city, student.city);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + gender;
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + yearOfBirth;
        result = 31 * result + (monthOfBirth != null ? monthOfBirth.hashCode() : 0);
        result = 31 * result + dayOfBirth;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + hobby;
        result = 31 * result + (currentAddress != null ? currentAddress.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", monthOfBirth=" + monthOfBirth +
                ", dayOfBirth=" + dayOfBirth +
                ", subject='" + subject + '\'' +
                ", hobby=" + hobby +
                ", currentAddress='" + currentAddress + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
