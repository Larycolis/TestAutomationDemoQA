package org.base;

import com.codeborne.selenide.ClickOptions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPage {
    public FormPage inputFirstName(String firstName) {
        sendKey("firstName", firstName);
        return this;
    }

    public FormPage inputLastName(String lastName) {
        sendKey("lastName", lastName);
        return this;
    }

    public FormPage inputEmail(String email) {
        sendKey("userEmail", email);
        return this;
    }

    public FormPage selectGender(int option) {
        $(By.id("gender-radio-" + option)).click(ClickOptions.usingJavaScript());
        return this;
    }

    public FormPage inputUserNumber(String number) {
        sendKey("userNumber", number);
        return this;
    }

    public FormPage inputDateOfBirth(String date) {
        sendKey("dateOfBirthInput", date);
        return this;
    }

    public FormPage inputSubjects(String subject) {
        sendKey("subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3", subject);
        return this;
    }

    public FormPage selectHobbies(int option) {
        $(By.id("hobbies-checkbox-" + option)).click(ClickOptions.usingJavaScript());
        return this;
    }

    public FormPage inputCurrentAddress(String address) {
        sendKey("currentAddress", address);
        return this;
    }

    public FormPage inputState(String state) {
        sendKey("state", state);
        return this;
    }

    public FormPage inputCity(String city) {
        sendKey("city", city);
        return this;
    }

    public FormPage submitRegistrationForm() {
        $(By.id("submit")).submit();
        return this;
    }

    public FormPage checkGenderActivity() {
        $$("[id='genterWrapper'] input.custom-control-input")
                .filter(selected)
                .shouldHave(size(0));
        $$("[id='genterWrapper'] label.custom-control-label")
                .filter(cssValue("color", "rgba(220, 53, 69, 1)"))
                .shouldHave(size(3));
        return this;
    }

    public FormPage checkFirstNameActivity() {
        checkCSSValueByElementId("firstName");
        return this;
    }

    public FormPage checkLastNameActivity() {
        checkCSSValueByElementId("lastName");
        return this;
    }

    public void checkUserNumberActivity() {
        checkCSSValueByElementId("userNumber");
    }

    private void sendKey(String id, String key) {
        $(By.id(id)).sendKeys(key);
    }

    private void checkCSSValueByElementId(String firstName) {
        $(By.id(firstName)).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
