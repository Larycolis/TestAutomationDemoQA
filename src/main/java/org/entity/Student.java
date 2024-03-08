package org.entity;

import lombok.*;

@Builder
@Data //Getters and Setters
@ToString
@EqualsAndHashCode(exclude = {"state", "city"})
public class Student {
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
}
