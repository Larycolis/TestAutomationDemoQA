package org.base;

import org.entity.Student;
import org.openqa.selenium.By;

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

    public FormPage inputDateOfBirth() {
        $(By.id("dateOfBirthInput")).click(usingJavaScript());
        $(By.className("react-datepicker__year-select")).find("option:nth-child(92)").click(); //1991
        $(By.className("react-datepicker__month-select")).find("option:nth-child(3)").click(); //March
        $("div.react-datepicker__month > div:nth-child(3) > div:nth-child(2)").click(usingJavaScript()); //11
        return this;
    }

    public FormPage inputSubjects(Student testStudent) {
        sendKeyById("subjectsInput", testStudent.getSubject());
        $(By.id("subjectsInput")).pressEnter();
        return this;
    }

    public FormPage selectHobbies(Student testStudent) {
        $(By.id("hobbies-checkbox-" + testStudent.getHobby())).click(usingJavaScript());
        return this;
    }

    public FormPage uploadFile(String pictureName) {
        $(By.id("uploadPicture")).sendKeys(pictureName);
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
        $(By.id("react-select-4-input")).pressEnter();
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
}
