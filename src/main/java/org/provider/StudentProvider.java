package org.provider;

import com.github.javafaker.Faker;
import org.entity.Student;
import org.helper.StudentBuilder;

public class StudentProvider {
    //TODO: создать обертку над Faker
    private static final Faker FAKER = new Faker();
    private static final String DATE_OF_BIRTH = "11 March,1991";

    public static Student getStudentWithRequiredFields() {
        return createStudentWithRequiredFields().build();
    }

    public static StudentBuilder getStudentWithAllFieldsWithoutUpload() {
        return createStudentWithRequiredFields()
                .email(FAKER.internet().emailAddress())
                .hobby(getRandomNumber())
                .currentAddress(FAKER.address().fullAddress());
    }

    public static Student getStudentWithAllFieldsWithUpload(String ... params) {
        return createStudentWithRequiredFields()
                .email(FAKER.internet().emailAddress())
                .subject(params[0])
                .hobby(getRandomNumber())
                .pictureName(params[1])
                .currentAddress(FAKER.address().fullAddress())
                .state(params[2])
                .city(params[3])
                .stateAndCity(params[2] + " " + params[3])
                .build();
    }

    public static int getRandomNumber(){
        return FAKER.number().numberBetween(1, 3);
    }

    private static StudentBuilder createStudentWithRequiredFields() {
        return new StudentBuilder()
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .mobileNumber(FAKER.phoneNumber().subscriberNumber(10))
                .dateOfBirth(DATE_OF_BIRTH)
                .gender(getRandomNumber());
    }
}
