package ui.elements;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.page.elements.CheckboxPage;

@ExtendWith(AllureJunit5.class)
@Feature("Checkbox")
@Story("UI Tests")
public class CheckboxTests extends BaseTest {
    private CheckboxPage checkboxPage;
    @BeforeEach
    void setUp() {
        checkboxPage = new CheckboxPage(getDriver());
        getDriver().get("https://demoqa.com/checkbox");
    }

    @Test
    @DisplayName("Click on the root checkbox and check that all checkboxes are selected")
    void clickRootCheckboxAndCheckAllCheckboxesIsSelected() {
        checkboxPage.clickExpandAll();
        checkboxPage.clickRootCheckBox();
        checkboxPage.checkEqualsExpAndActTitles();
        checkboxPage.checkSizeEquality();
        checkboxPage.checkSelectedCheckBoxes();
    }
}
