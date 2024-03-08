package ui.forms;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.FormPage;
import org.base.ModalWindowForm;
import org.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static com.codeborne.selenide.Configuration.headless;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.provider.StudentProvider.*;
import static org.util.FakerUtil.*;

public class PracticeFormTests {
    private final String subject = "English";
    private final String state = "Uttar Pradesh";
    private final String city = "Agra";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        headless = true;
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

    @Test
    void fillAllFieldsWithUploadAndCheckExpectedMatchesActualTest() {
        String fullPath = generateTempFile(".jpg");
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
        Student testStudent = Student.builder()
                .lastName(getLastName())
                .gender(getRandomNumber())
                .mobileNumber(getMobilNumber())
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
        Student testStudent = Student.builder()
                .firstName(getFirstName())
                .gender(getRandomNumber())
                .mobileNumber(getMobilNumber())
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
    void fillRequiredFieldsExceptionOfUserNumberAndCheckWarningTest(String userNumber) {
        Student testStudent = Student.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
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
        Student testStudent = Student.builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .mobileNumber(getMobilNumber())
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

    private String getFileName(String fullPath) {
        return new File(fullPath).getName();
    }
}
