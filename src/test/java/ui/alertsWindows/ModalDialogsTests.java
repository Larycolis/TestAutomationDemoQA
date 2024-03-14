package ui.alertsWindows;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.page.alertsWindows.ModalDialogsPage;

@ExtendWith(AllureJunit5.class)
@Feature("Modal Dialogs")
@Story("UI Tests")
public class ModalDialogsTests extends BaseTest {
    private ModalDialogsPage modalDialogsPage;

    @BeforeEach
    void setUp() {
        modalDialogsPage = new ModalDialogsPage(getDriver());
        getDriver().get("https://demoqa.com/modal-dialogs");
    }

    @Test
    @DisplayName("Click the button to see the small modal")
    void clickSmallModalButtonAndCheckOpenNewSmallModalTest() {
        modalDialogsPage.clickAndWaitSmallModalWindow();
        modalDialogsPage.checkEqualsExpAndActTitleSmallModal();
        modalDialogsPage.checkEqualsExpAndActBodyTextSmallModal();
        modalDialogsPage.closeSmallModalAndCheckModalWindowIsInvisibility();
    }

    @Test
    @DisplayName("Click the button to see the large modal")
    void clickLargeModalButtonAndCheckOpenNewLargeModalTest() {
        modalDialogsPage.clickAndWaitLargeModalWindow();
        modalDialogsPage.checkEqualsExpAndActTitleLargeModal();
        modalDialogsPage.checkEqualsExpAndActBodyTextLargeModal();
        modalDialogsPage.closeLargeModalAndCheckModalWindowIsInvisibility();
    }
}
