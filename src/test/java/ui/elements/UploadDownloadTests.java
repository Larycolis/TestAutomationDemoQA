package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.page.elements.UploadDownloadPage;

public class UploadDownloadTests extends BaseTest {
    private UploadDownloadPage uploadDownloadPage;

    @BeforeEach
    void setUp() {
        uploadDownloadPage = new UploadDownloadPage(getDriver());
        getDriver().get("https://demoqa.com/upload-download");
    }

    @Test
    @DisplayName("Downloading the file")
    void clickDownloadAndCheckSampleFileIsDownloadedTest() {
        uploadDownloadPage.clickDownloadButton();
        uploadDownloadPage.checkAndDeleteDownloadedFile();
    }

    @ParameterizedTest
    @ValueSource(strings = {".txt", ".jpg", ".pdf", ".doc", ".xlsx"})
    @DisplayName("Uploading the files with different extensions")
    void clickChooseFileAndUploadFileTest(String ext) {
        uploadDownloadPage.sendPathAndAssertTextOnScreen(ext);
    }
}
