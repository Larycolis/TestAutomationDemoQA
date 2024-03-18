package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.page.elements.RadioButtonPage;

public class RadioButtonTests extends BaseTest {
    private RadioButtonPage radioButtonPage;

    @BeforeEach
    void setUp() {
        radioButtonPage = new RadioButtonPage(getDriver());
        getDriver().get("https://demoqa.com/radio-button");
    }

    @Test
    @DisplayName("Click the button Yes and check the text")
    void clickButtonYesAndCheckSelectedTest() {
        radioButtonPage.assertTextAfterClickButtonYes();
    }

    @Test
    @DisplayName("Click the button Impressive and check the text")
    void clickButtonImpressiveAndCheckSelectedTest() {
        radioButtonPage.assertTextAfterClickButtonImpressive();
    }

    @Test
    @DisplayName("Check button No is unavailable")
    void checkButtonNoIsUnavailableTest() {
        radioButtonPage.assertButtonIsUnavailable();
    }
}
