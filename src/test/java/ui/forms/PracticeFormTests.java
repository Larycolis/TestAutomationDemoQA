package ui.forms;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.FormPage;
import org.base.ModalWindowForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PracticeFormTests {
    private static final String FIRST_NAME_VALUE = "TestFirstName";
    private static final String LAST_NAME_VALUE = "TestLastName";
    private static final String USER_NUMBER_VALUE = "0123456789";
    private static final String EMAIL_VALUE = "test_demoqa@gmail.com";
    private static final String DATE_OF_BIRTH_VALUE = "26 Jul 1960";
    private static final String[] SUBJECT_VALUE = {"English", "Biology", "History"};
    private static final String USER_CURRANT_ADDRESS_VALUE = "66 Perry Street, NYC";
    private static final String STATE_VALUE = "NCR";
    private static final String CITY_VALUE = "Delhi";

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

    @Test
    void fillRequiredFieldsExceptionOfFirstNameAndCheckWarningTest() {
        page(FormPage.class)
                .inputLastName(LAST_NAME_VALUE)
                .selectGender(2)
                .inputUserNumber(USER_NUMBER_VALUE)
                .submitRegistrationForm()
                .checkFirstNameActivity();
    }

    @Test
    void fillRequiredFieldsExceptionOfLastNameAndCheckWarningTest() {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .selectGender(3)
                .inputUserNumber(USER_NUMBER_VALUE)
                .submitRegistrationForm()
                .checkLastNameActivity();
    }

    @ParameterizedTest
    @ValueSource(strings = {"012345678", "", "Number", "!@#&^"})
    void fillRequiredFieldsExceptionOfUserNumberAndCheckWarningTest(String userNumber) {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .selectGender(1)
                .inputUserNumber(userNumber)
                .submitRegistrationForm()
                .checkUserNumberActivity();
    }

    @Test
    void fillRequiredFieldsExceptionOfGenderAndCheckWarningTest() {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .inputUserNumber(USER_NUMBER_VALUE)
                .submitRegistrationForm()
                .checkGenderActivity();
    }

    @Test
    void clickSubmitWithoutFillingOutTheFormFieldsTest() {
        page(FormPage.class)
                .submitRegistrationForm()
                .checkFirstNameActivity()
                .checkLastNameActivity()
                .checkGenderActivity()
                .checkUserNumberActivity();
    }

    @Test
    void fillAllFieldsWithoutDownloadAndCheckModalWindowIsVisibleTest() {
        page(FormPage.class)
                .inputFirstName(FIRST_NAME_VALUE)
                .inputLastName(LAST_NAME_VALUE)
                .inputEmail(EMAIL_VALUE)
                .selectGender(2)
                .inputUserNumber(USER_NUMBER_VALUE)
                //.inputDateOfBirth(DATE_OF_BIRTH_VALUE)
                //.inputSubjects(SUBJECT_VALUE[0])
                .selectHobbies(1)
                .inputCurrentAddress(USER_CURRANT_ADDRESS_VALUE)
                //.inputState(STATE_VALUE)
                //.inputCity(CITY_VALUE)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .checkModalWindowIsVisible();
    }
}
