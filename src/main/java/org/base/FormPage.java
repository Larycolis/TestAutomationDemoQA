package org.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.entity.Student;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.*;

public class FormPage {
    public FormPage inputFirstName(Student testStudent) {
        sendKeyById("firstName", testStudent.getFirstName());
        return this;
    }

    public FormPage inputLastName(Student testStudent) {
        sendKeyById("lastName", testStudent.getLastName());
        return this;
    }

    public FormPage inputEmail(Student testStudent) {
        sendKeyById("userEmail", testStudent.getEmail());
        return this;
    }

    public FormPage selectGender(Student testStudent) {
        $(By.id("gender-radio-" + testStudent.getGender())).click(usingJavaScript());
        return this;
    }

    public FormPage inputUserNumber(Student testStudent) {
        sendKeyById("userNumber", testStudent.getMobileNumber());
        return this;
    }

    public FormPage inputDateOfBirth(Student testStudent) {
        $(By.id("dateOfBirthInput")).click(usingJavaScript());
        $(By.className("react-datepicker__year-select")).find("option:nth-child(" + testStudent.getYearOfBirth() + ")").click();
        $(By.className("react-datepicker__month-select")).find("option:nth-child(" + testStudent.getMonthOfBirth() + ")").click();
        $("div.react-datepicker__month > div:nth-child(3) > div:nth-child(2)").click();
        return this;
    }

    public FormPage inputSubjects(Student testStudent) {
        sendKeyById("subjectsInput", testStudent.getSubject());
        return this;
    }

    public FormPage selectHobbies(Student testStudent) {
        $(By.id("hobbies-checkbox-" + testStudent.getHobby())).click(usingJavaScript());
        return this;
    }

    public FormPage uploadFile(String ext) {
        $(By.id("uploadPicture")).sendKeys(generateTempFile(ext));
        return this;
    }

    public FormPage inputCurrentAddress(Student testStudent) {
        sendKeyById("currentAddress", testStudent.getCurrentAddress());
        return this;
    }

    public FormPage inputLocation(Student testStudent) {
        sendKeyById("react-select-3-input", testStudent.getState());
        $(By.id("react-select-3-input")).pressEnter();
        sendKeyById("react-select-4-input", testStudent.getCity());
        return this;
    }

    public FormPage submitRegistrationForm() {
        $(By.id("submit")).submit();
        return this;
    }

    public FormPage checkGenderWarningActivity() {
        $$("input[name='gender']")
                .filter(selected)
                .shouldHave(size(0));
        $$x("//label[contains(@for,'gender')]")
                .filter(cssValue("color", "rgba(220, 53, 69, 1)"))
                .shouldHave(size(3));
        return this;
    }

    public FormPage checkFirstNameWarningActivity() {
        checkWarningCSSValueByElementId("firstName");
        return this;
    }

    public FormPage checkLastNameWarningActivity() {
        checkWarningCSSValueByElementId("lastName");
        return this;
    }

    public void checkUserNumberWarningActivity() {
        checkWarningCSSValueByElementId("userNumber");
    }

    private void sendKeyById(String id, String key) {
        $(By.id(id)).sendKeys(key);
    }

    private void checkWarningCSSValueByElementId(String id) {
        $(By.id(id)).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    private String generateTempFile(String ext) {
        try {
            return Files.createTempFile(RandomStringUtils.randomAlphanumeric(5), ext).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
