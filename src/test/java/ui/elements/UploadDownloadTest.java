package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadDownloadTest extends BaseTest {
    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/upload-download");
    }

    @Test
    void clickDownloadAndCheckSampleFileIsDownloadedTest() {
        getDriver().findElement(By.id("downloadButton")).click();
        checkAndDeleteDownloadedFile();
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
