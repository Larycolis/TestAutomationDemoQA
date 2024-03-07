package ui.forms;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.base.FormPage;
import org.base.ModalWindowForm;
import org.entity.Student;
import org.helper.StudentBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.provider.StudentProvider.*;

public class PracticeFormTests {
    private final Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void fillAllRequiredFieldsAndCheckExpectedMatchesActualTest() {
        Student testStudent = getStudentWithRequiredFields();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .inputDateOfBirth()
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .compareActualAndExpectedStudent(testStudent);
    }

    @Test
    void fillAllFieldsWithoutUploadAndCheckExpectedMatchesActualTest() {
        Student testStudent = getStudentWithAllFieldsWithoutUpload()
                .subject("English")
                .state("Uttar Pradesh")
                .city("Agra")
                .stateAndCity("Uttar Pradesh Agra")
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .inputEmail(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .inputDateOfBirth()
                .inputSubjects(testStudent)
                .selectHobbies(testStudent)
                .inputCurrentAddress(testStudent)
                .inputLocation(testStudent)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .compareActualAndExpectedStudent(testStudent);
    }

    @Test
    void fillAllFieldsWithUploadAndCheckExpectedMatchesActualTest() {
        String fullPath = generateTempFile(".jpg");
        String pictureName = getFileName(fullPath);
        Student testStudent = getStudentWithAllFieldsWithUpload("English", pictureName, "Uttar Pradesh", "Agra");
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .inputEmail(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .inputDateOfBirth()
                .inputSubjects(testStudent)
                .selectHobbies(testStudent)
                .uploadFile(fullPath)
                .inputCurrentAddress(testStudent)
                .inputLocation(testStudent)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .compareActualAndExpectedStudent(testStudent);
    }

    @ParameterizedTest
    @CsvSource({"English, NCR, Delhi", "Biology, Uttar Pradesh, Agra",
            "History, Rajasthan, Jaiselmer", "Physics, Haryana, Panipat"})
    void fillAllFieldsVariantsWithoutUploadAndCheckExpectedMatchesActualTest(String subject, String state, String city) {
        Student testStudent = getStudentWithAllFieldsWithoutUpload()
                .subject(subject)
                .state(state)
                .city(city)
                .stateAndCity(state + " " + city)
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .inputEmail(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .inputDateOfBirth()
                .inputSubjects(testStudent)
                .selectHobbies(testStudent)
                .inputCurrentAddress(testStudent)
                .inputLocation(testStudent)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .compareActualAndExpectedStudent(testStudent);
    }

    @ParameterizedTest
    @CsvSource({"English, .jpg, NCR, Delhi", "Biology, .pdf, Uttar Pradesh, Agra",
            "History, .tif, Rajasthan, Jaiselmer", "Physics, .bmp, Haryana, Panipat"})
    void fillAllFieldsVariantsWithUploadAndCheckExpectedMatchesActualTest(String subject, String ext, String state, String city) {
        String fullPath = generateTempFile(ext);
        String pictureName = getFileName(fullPath);
        Student testStudent = getStudentWithAllFieldsWithUpload(subject, pictureName, state, city);
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .inputEmail(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .inputDateOfBirth()
                .inputSubjects(testStudent)
                .selectHobbies(testStudent)
                .uploadFile(fullPath)
                .inputCurrentAddress(testStudent)
                .inputLocation(testStudent)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .compareActualAndExpectedStudent(testStudent);
    }

    @Test
    void fillRequiredFieldsExceptionOfFirstNameAndCheckWarningTest() {
        Student testStudent = new StudentBuilder()
                .lastName(faker.name().lastName())
                .gender(getRandomNumber())
                .mobileNumber(faker.phoneNumber().subscriberNumber(10))
                .build();
        page(FormPage.class)
                .inputLastName(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .submitRegistrationForm()
                .checkFirstNameWarningActivity();
    }

    @Test
    void fillRequiredFieldsExceptionOfLastNameAndCheckWarningTest() {
        Student testStudent = new StudentBuilder()
                .firstName(faker.name().firstName())
                .gender(getRandomNumber())
                .mobileNumber(faker.phoneNumber().subscriberNumber(10))
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .submitRegistrationForm()
                .checkLastNameWarningActivity();
    }

    @ParameterizedTest
    @ValueSource(strings = {"012345678", "Number", "!@#&^"})
        //с провайдером
    void fillRequiredFieldsExceptionOfUserNumberAndCheckWarningTest(String userNumber) {
        Student testStudent = new StudentBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .gender(getRandomNumber())
                .mobileNumber(userNumber)
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .submitRegistrationForm()
                .checkUserNumberWarningActivity();
    }

    @Test
    void fillRequiredFieldsExceptionOfGenderAndCheckWarningTest() {
        Student testStudent = new StudentBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .mobileNumber(faker.phoneNumber().subscriberNumber(10))
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .inputUserNumber(testStudent)
                .submitRegistrationForm()
                .checkGenderWarningActivity();
    }

    @Test
    void clickSubmitWithoutFillingOutTheFormFieldsTest() {
        page(FormPage.class)
                .submitRegistrationForm()
                .checkFirstNameWarningActivity()
                .checkLastNameWarningActivity()
                .checkGenderWarningActivity()
                .checkUserNumberWarningActivity();
    }

    private String generateTempFile(String ext) {
        try {
            return Files.createTempFile(RandomStringUtils.randomAlphanumeric(5), ext).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileName(String fullPath) {
        return new File(fullPath).getName();
    }
}
