package org.base;

import org.openqa.selenium.By;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.*;

public class FormPage {
    public FormPage inputFirstName(String firstName) {
        sendKeyById("firstName", firstName);
        return this;
    }

    public FormPage inputLastName(String lastName) {
        sendKeyById("lastName", lastName);
        return this;
    }

    public FormPage inputEmail(String email) {
        sendKeyById("userEmail", email);
        return this;
    }

    public FormPage selectGender(int option) {
        $(By.id("gender-radio-" + option)).click(usingJavaScript());
        return this;
    }

    public FormPage inputUserNumber(String number) {
        sendKeyById("userNumber", number);
        return this;
    }

    public FormPage inputDateOfBirth(int optionYear, int optionMonth) {
        $(By.id("dateOfBirthInput")).click(usingJavaScript());
        $(By.className("react-datepicker__year-select")).find("option:nth-child(" + optionYear + ")").click();
        $(By.className("react-datepicker__month-select")).find("option:nth-child(" + optionMonth + ")").click();
        $("div.react-datepicker__month > div:nth-child(3) > div:nth-child(2)").click();
        return this;
    }

    public FormPage inputSubjects(String subject) {
        sendKeyById("subjectsInput", subject);
        return this;
    }

    public FormPage selectHobbies(int option) {
        $(By.id("hobbies-checkbox-" + option)).click(usingJavaScript());
        return this;
    }

    public FormPage inputCurrentAddress(String address) {
        sendKeyById("currentAddress", address);
        return this;
    }

    public FormPage inputState(String state) {
        sendKeyById("react-select-3-input", state);
        $(By.id("react-select-3-input")).pressEnter();
        return this;
    }

    public FormPage inputCity(String city) {
        sendKeyById("react-select-4-input", city);
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
