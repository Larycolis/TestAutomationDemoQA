package ui.forms;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.FormPage;
import org.base.ModalWindowForm;
import org.entity.Student;
import org.helper.StudentBuilder;
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

    // вынести студента с более повторяемыми полями в методах добавлять параметры
    // в этого студента
    @Test
    void fillAllRequiredFieldsAndCheckModalWindowIsVisibleTest() {
        Student testStudent = new StudentBuilder()
                .firstName(FIRST_NAME_VALUE)
                .lastName(LAST_NAME_VALUE)
                .gender(1)
                .mobileNumber(USER_NUMBER_VALUE)
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .checkModalWindowIsVisible();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void fillRequiredFieldsExceptionOfFirstNameAndCheckWarningTest(int gender) {
        Student testStudent = new StudentBuilder()
                .lastName(LAST_NAME_VALUE)
                .gender(gender)
                .mobileNumber(USER_NUMBER_VALUE)
                .build();
        page(FormPage.class)
                .inputLastName(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .submitRegistrationForm()
                .checkFirstNameWarningActivity();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void fillRequiredFieldsExceptionOfLastNameAndCheckWarningTest(int gender) {
        Student testStudent = new StudentBuilder()
                .firstName(FIRST_NAME_VALUE)
                .gender(gender)
                .mobileNumber(USER_NUMBER_VALUE)
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .submitRegistrationForm()
                .checkLastNameWarningActivity();
    }

    @ParameterizedTest
    @CsvSource({"1, 012345678", "2, Number", "3, !@#&^"})
    void fillRequiredFieldsExceptionOfUserNumberAndCheckWarningTest(int gender, String userNumber) {
        Student testStudent = new StudentBuilder()
                .firstName(FIRST_NAME_VALUE)
                .lastName(LAST_NAME_VALUE)
                .gender(gender)
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
                .firstName(FIRST_NAME_VALUE)
                .lastName(LAST_NAME_VALUE)
                .mobileNumber(USER_NUMBER_VALUE)
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

    @ParameterizedTest
    @CsvSource({"1, 1, 5, English, 2, NCR, Delhi",
            "3, 201, 1, Biology, 1, Uttar Pradesh, Agra",
            "2, 92, 11, History, 3, Rajasthan, Jaiselmer",
            "1, 92, 1, Physics, 3, Haryana, Panipat"})
    void fillAllFieldsWithoutUploadAndCheckModalWindowIsVisibleTest(int gender, int year, int month,
                                                                    String subject, int hobby, String state,
                                                                    String city) {
        Student testStudent = new StudentBuilder()
                .firstName(FIRST_NAME_VALUE)
                .lastName(LAST_NAME_VALUE)
                .email(EMAIL_VALUE)
                .gender(gender)
                .mobileNumber(USER_NUMBER_VALUE)
                .yearOfBirth(year)
                .monthOfBirth(month)
                .subject(subject)
                .hobby(hobby)
                .currentAddress(USER_CURRANT_ADDRESS_VALUE)
                .state(state)
                .city(city)
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .inputEmail(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .inputDateOfBirth(testStudent) //Todo: подумать про LocalDateTime и работать с полной датой: год, месяц, день
                .inputSubjects(testStudent)
                .selectHobbies(testStudent)
                .inputCurrentAddress(testStudent)
                .inputLocation(testStudent) //Todo: надо подумать как использовать enum StateValue, CityValue
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
        Student testStudent = new StudentBuilder()
                .firstName(FIRST_NAME_VALUE)
                .lastName(LAST_NAME_VALUE)
                .email(EMAIL_VALUE)
                .gender(gender)
                .mobileNumber(USER_NUMBER_VALUE)
                .yearOfBirth(year)
                .monthOfBirth(month)
                .subject(subject)
                .hobby(hobby)
                .currentAddress(USER_CURRANT_ADDRESS_VALUE)
                .state(state)
                .city(city)
                .build();
        page(FormPage.class)
                .inputFirstName(testStudent)
                .inputLastName(testStudent)
                .inputEmail(testStudent)
                .selectGender(testStudent)
                .inputUserNumber(testStudent)
                .inputDateOfBirth(testStudent) //Todo: подумать про LocalDateTime и работать с полной датой: год, месяц, день
                .inputSubjects(testStudent)
                .selectHobbies(testStudent)
                .uploadFile(ext)
                .inputCurrentAddress(testStudent)
                .inputLocation(testStudent) //Todo: надо подумать как использовать enum StateValue, CityValue
                .submitRegistrationForm();
        page(ModalWindowForm.class)
                .checkModalWindowIsVisible();
    }
}
