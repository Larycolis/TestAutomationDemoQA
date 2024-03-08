package org.provider;

import org.entity.Student;

import static org.util.FakerUtil.*;

public class StudentProvider {
    private static final String DATE_OF_BIRTH = "11 March,1991";

    public static Student getStudentWithRequiredFields() {
        return createStudentWithRequiredFields().build();
    }

    public static Student.StudentBuilder getStudentWithAllFieldsWithoutUpload() {
        return createStudentWithRequiredFields()
                .email(getEmailAddress())
                .hobby(getRandomNumber())
                .currentAddress(getFullAddress());
    }

    public static Student getStudentWithAllFieldsWithUpload(String ... params) {
        return createStudentWithRequiredFields()
                .email(getEmailAddress())
                .subject(params[0])
                .hobby(getRandomNumber())
                .pictureName(params[1])
                .currentAddress(getFullAddress())
                .state(params[2])
                .city(params[3])
                .stateAndCity(params[2] + " " + params[3])
                .build();
    }

    private static Student.StudentBuilder createStudentWithRequiredFields() {
        return Student.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .mobileNumber(getMobilNumber())
                .dateOfBirth(DATE_OF_BIRTH)
                .gender(getRandomNumber());
    }
}
