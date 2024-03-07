package org.provider;

import com.github.javafaker.Faker;
import org.entity.Student;
import org.helper.StudentBuilder;

public class StudentProvider {
    //TODO: создать приватные методы для заполнения повторяющихся полей
    private static final Faker FAKER = new Faker();
    private static final String DATE_OF_BIRTH = "11 March,1991";

    public static Student getStudentWithRequiredFields() {
        return new StudentBuilder()
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .mobileNumber(FAKER.phoneNumber().subscriberNumber(10))
                .dateOfBirth(DATE_OF_BIRTH)
                .gender(getRandomNumber())
                .build();
    }

    public static StudentBuilder getStudentWithAllFieldsWithoutUpload() {
        return new StudentBuilder()
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .email(FAKER.internet().emailAddress())
                .gender(getRandomNumber())
                .mobileNumber(FAKER.phoneNumber().subscriberNumber(10))
                .dateOfBirth(DATE_OF_BIRTH)
                .hobby(getRandomNumber())
                .currentAddress(FAKER.address().fullAddress());
    }

    public static Student getStudentWithAllFieldsWithUpload(String subject, String pictureName, String state, String city) {
        return new StudentBuilder()
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .email(FAKER.internet().emailAddress())
                .gender(getRandomNumber())
                .mobileNumber(FAKER.phoneNumber().subscriberNumber(10))
                .dateOfBirth(DATE_OF_BIRTH)
                .subject(subject)
                .hobby(getRandomNumber())
                .pictureName(pictureName)
                .currentAddress(FAKER.address().fullAddress())
                .state(state)
                .city(city)
                .stateAndCity(state + " " + city)
                .build();
    }

    public static int getRandomNumber(){
        return FAKER.number().numberBetween(1, 3);
    }
}
