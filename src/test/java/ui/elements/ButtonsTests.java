package ui.elements;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.page.elements.ButtonsPage;

@ExtendWith(AllureJunit5.class)
@Feature("Buttons")
@Story("UI Tests")
public class ButtonsTests extends BaseTest {
    private ButtonsPage buttonsPage;
    @BeforeEach
    void setUp() {
        buttonsPage = new ButtonsPage(getDriver());
        getDriver().get("https://demoqa.com/buttons");
    }

    @Test
    @DisplayName("Check double left button click action")
    void clickButtonDoubleClickMeAndCheckSelectedTest() {
        buttonsPage.doubleClick();
        buttonsPage.assertTextAfterDoubleClick();
    }

    @Test
    @DisplayName("Check right button click action")
    void clickButtonRightClickMeAndCheckSelectedTest() {
        buttonsPage.rightClick();
        buttonsPage.assertTextAfterRightClick();
    }

    @Test
    @DisplayName("Check left button click action")
    void clickButtonClickMeAndCheckSelectedTest() {
        buttonsPage.clickAndAssertTextAfterLeftClick();
    }
}
