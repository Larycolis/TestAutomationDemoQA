package ui.elements;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.page.elements.DynamicPropertiesPage;

@ExtendWith(AllureJunit5.class)
@Feature("Dynamic Properties")
@Story("UI Tests")
public class DynamicPropertiesTests extends BaseTest {
    private DynamicPropertiesPage dynamicPropertiesPage;
    @BeforeEach
    void setUp() {
        dynamicPropertiesPage = new DynamicPropertiesPage(getDriver());
        getDriver().get("https://demoqa.com/dynamic-properties");
    }

    @Test
    @DisplayName("Checking that the text is equals")
    void checkTextIsDisplayed() {
        dynamicPropertiesPage.assertDisplayedTextWithRandomId();
    }

    @Test
    @DisplayName("Wait until the button becomes enable")
    void waitUntilEnabledAndClickTest() {
        dynamicPropertiesPage.checkClickableWillEnabledButton();
    }

    @Test
    @DisplayName("Wait until the button changes color")
    void waitUntilColorChangedAndClickTest() {
        dynamicPropertiesPage.checkColorChange();
    }

    @Test
    @DisplayName("Wait until the button becomes visible")
    void waitUntilVisibleAndClickTest() {
        dynamicPropertiesPage.checkVisibility();
    }
}
