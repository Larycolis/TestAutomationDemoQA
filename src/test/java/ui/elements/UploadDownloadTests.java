package ui.elements;

import org.apache.commons.lang3.RandomStringUtils;
import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadDownloadTests extends BaseTest {
    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/upload-download");
    }

    @Test
    void clickDownloadAndCheckSampleFileIsDownloadedTest() {
        getDriver().findElement(By.id("downloadButton")).click();
        checkAndDeleteDownloadedFile();
    }

    @ParameterizedTest
    @ValueSource(strings = {".txt", ".jpg", ".pdf", ".doc", ".xlsx"})
    void clickChooseFileAndUploadFileTest(String ext) {
        WebElement uploadElement = getDriver().findElement(By.id("uploadFile"));
        Path tempFile = generateTempFile(ext);
        uploadElement.sendKeys(tempFile.toString());
        Assertions.assertEquals("C:\\fakepath\\" + tempFile.getFileName(),
                getDriver().findElement(By.id("uploadedFilePath")).getText());
    }

    private Path generateTempFile(String ext) {
        try {
            return Files.createTempFile(RandomStringUtils.randomAlphanumeric(5), ext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkAndDeleteDownloadedFile() {
        Path filePath = Paths.get("D:\\Downloads", "sampleFile.jpeg");
        for (int i = 0; i < 10; i++) {
            if (Files.exists(filePath)) {
                try {
                    Files.delete(filePath);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
