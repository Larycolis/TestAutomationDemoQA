package ui.forms;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.FormPage;
import org.base.ModalWindowForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PracticeFormTests {
    private static final String FIRST_NAME_VALUE = "TestFirstName";
    private static final String LAST_NAME_VALUE = "TestLastName";
    private static final String USER_NUMBER_VALUE = "0123456789";
    private static final String EMAIL_VALUE = "test_demoqa@gmail.com";
    private static final String USER_CURRANT_ADDRESS_VALUE = "66 Perry Street, NYC";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void fillAllRequiredFieldsAndCheckModalWindowIsVisibleTest() {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .selectGender(1)
                .inputUserNumber(USER_NUMBER_VALUE)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .checkModalWindowIsVisible();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void fillRequiredFieldsExceptionOfFirstNameAndCheckWarningTest(int gender) {
        page(FormPage.class)
                .inputLastName(LAST_NAME_VALUE)
                .selectGender(gender)
                .inputUserNumber(USER_NUMBER_VALUE)
                .submitRegistrationForm()
                .checkFirstNameWarningActivity();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void fillRequiredFieldsExceptionOfLastNameAndCheckWarningTest(int gender) {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .selectGender(gender)
                .inputUserNumber(USER_NUMBER_VALUE)
                .submitRegistrationForm()
                .checkLastNameWarningActivity();
    }

    @ParameterizedTest
    @CsvSource({"1, 012345678", "2, Number", "3, !@#&^"})
    void fillRequiredFieldsExceptionOfUserNumberAndCheckWarningTest(int gender, String userNumber) {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .selectGender(gender)
                .inputUserNumber(userNumber)
                .submitRegistrationForm()
                .checkUserNumberWarningActivity();
    }

    @Test
    void fillRequiredFieldsExceptionOfGenderAndCheckWarningTest() {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .inputUserNumber(USER_NUMBER_VALUE)
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

    @ParameterizedTest
    @CsvSource({"1, 1, 5, English, 2, NCR, Delhi",
            "3, 201, 1, Biology, 1, Uttar Pradesh, Agra",
            "2, 92, 11, History, 3, Rajasthan, Jaiselmer",
            "1, 92, 1, Physics, 3, Haryana, Panipat"})
    void fillAllFieldsWithoutUploadAndCheckModalWindowIsVisibleTest(int gender, int year, int month,
                                                                    String subject, int hobby, String state,
                                                                    String city) {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .inputEmail(EMAIL_VALUE)
                .selectGender(gender)
                .inputUserNumber(USER_NUMBER_VALUE)
                .inputDateOfBirth(year, month)
                .inputSubjects(subject)
                .selectHobbies(hobby)
                .inputCurrentAddress(USER_CURRANT_ADDRESS_VALUE)
                .inputLocation(state, city)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .checkModalWindowIsVisible();
    }

    @ParameterizedTest
    @CsvSource({"1, 1, 5, English, 2, .jpg, NCR, Delhi",
            "3, 201, 1, Biology, 1, .pdf, Uttar Pradesh, Agra",
            "2, 92, 11, History, 3, .tif, Rajasthan, Jaiselmer",
            "1, 92, 1, Physics, 3, .bmp, Haryana, Panipat"})
    void fillAllFieldsWithUploadAndCheckModalWindowIsVisibleTest(int gender, int year, int month,
                                                                 String subject, int hobby, String ext, String state,
                                                                 String city) {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .inputEmail(EMAIL_VALUE)
                .selectGender(gender)
                .inputUserNumber(USER_NUMBER_VALUE)
                .inputDateOfBirth(year, month)
                .inputSubjects(subject)
                .selectHobbies(hobby)
                .uploadFile(ext)
                .inputCurrentAddress(USER_CURRANT_ADDRESS_VALUE)
                .inputLocation(state, city)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .checkModalWindowIsVisible();
    }
}
