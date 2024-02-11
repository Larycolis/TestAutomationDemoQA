package ui.alertsWindows;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModalDialogsTests extends BaseTest {
    private static final String MODAL_WINDOW_CLASS_NAME = "modal-content";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/modal-dialogs");
    }

    @Test
    void clickSmallModalButtonAndCheckOpenNewSmallModalTest() {
        clickAndWaitModalWindow("showSmallModal");
        Assertions.assertEquals("Small Modal", getDriver().findElement(By.id("example-modal-sizes-title-sm")).getText());
        Assertions.assertEquals("This is a small modal. It has very less content", getDriver().findElement(By.className("modal-body")).getText());
        closeModalAndCheckModalWindowIsInvisibility("closeSmallModal");
    }

    @Test
    void clickLargeModalButtonAndCheckOpenNewLargeModalTest() {
        clickAndWaitModalWindow("showLargeModal");
        Assertions.assertEquals("Large Modal", getDriver().findElement(By.id("example-modal-sizes-title-lg")).getText());
        Assertions.assertEquals(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                        "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                        "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                        "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset " +
                        "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like " +
                        "Aldus PageMaker including versions of Lorem Ipsum.",
                getDriver().findElement(By.className("modal-body")).getText());
        closeModalAndCheckModalWindowIsInvisibility("closeLargeModal");
    }

    private void clickAndWaitModalWindow(String identifier) {
        getDriver().findElement(By.id(identifier)).click();
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.className(MODAL_WINDOW_CLASS_NAME)));
    }

    private void closeModalAndCheckModalWindowIsInvisibility(String identifier) {
        getDriver().findElement(By.id(identifier)).click();
        getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className(MODAL_WINDOW_CLASS_NAME)));
    }
}
