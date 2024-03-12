package ui.alertsWindows;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ExtendWith(AllureJunit5.class)
@Feature("Modal Dialogs")
@Story("UI Tests")
public class ModalDialogsTests extends BaseTest {
    private static final String MODAL_WINDOW_CLASS_NAME = "modal-content";
    private final String largeModalBodyText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
            "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
            "It has survived not only five centuries, but also the leap into electronic typesetting, " +
            "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset " +
            "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like " +
            "Aldus PageMaker including versions of Lorem Ipsum.";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/modal-dialogs");
    }

    @Test
    @DisplayName("Click the button to see the small modal")
    void clickSmallModalButtonAndCheckOpenNewSmallModalTest() {
        clickAndWaitModalWindow("showSmallModal");
        checkModalText("Small Modal", By.id("example-modal-sizes-title-sm"));
        checkModalText("This is a small modal. It has very less content", By.className("modal-body"));
        closeModalAndCheckModalWindowIsInvisibility("closeSmallModal");
    }

    @Test
    @DisplayName("Click the button to see the large modal")
    void clickLargeModalButtonAndCheckOpenNewLargeModalTest() {
        clickAndWaitModalWindow("showLargeModal");
        checkModalText("Large Modal", By.id("example-modal-sizes-title-lg"));
        checkModalText(largeModalBodyText, By.className("modal-body"));
        closeModalAndCheckModalWindowIsInvisibility("closeLargeModal");
    }

    @Step("Click the button and wait the modal")
    private void clickAndWaitModalWindow(String identifier) {
        getDriver().findElement(By.id(identifier)).click();
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.className(MODAL_WINDOW_CLASS_NAME)));
    }

    //TODO: порефачить тестовые классы, много где используется: BrokenLinksImagesTest, все собрать в одном месте (где?)
    @Step("Checking that the text is equals")
    private void checkModalText(String Small_Modal, By selector) {
        Assertions.assertEquals(Small_Modal, getDriver().findElement(selector).getText());
    }

    @Step("Close the modal and check it is invisible")
    private void closeModalAndCheckModalWindowIsInvisibility(String identifier) {
        getDriver().findElement(By.id(identifier)).click();
        getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className(MODAL_WINDOW_CLASS_NAME)));
    }
}
