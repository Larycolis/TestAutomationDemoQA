package org.util;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FakerUtil {
    private static final Faker FAKER = new Faker();

    public static String getFirstName() {
        return FAKER.name().firstName();
    }

    public static String getLastName() {
        return FAKER.name().lastName();
    }

    public static Integer getAge() {
        return FAKER.number().numberBetween(1, 99);
    }

    public static Integer getSalary() {
        return FAKER.number().numberBetween(1, 15000);
    }

    public static String getJob() {
        return FAKER.job().field();
    }
    public static String getMobilNumber() {
        return FAKER.phoneNumber().subscriberNumber(10);
    }

    public static String getEmailAddress() {
        return FAKER.internet().emailAddress();
    }

    public static String getFullAddress() {
        return FAKER.address().fullAddress();
    }

    public static int getRandomNumber() {
        return FAKER.number().numberBetween(1, 3);
    }

    public static Path generateTempFile(String ext) {
        try {
            return Files.createTempFile(RandomStringUtils.randomAlphanumeric(5), ext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
