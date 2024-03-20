package org.page.elements;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.util.FakerUtil.generateTempFile;

public class UploadDownloadPage {
    private final WebDriver driver;
    @FindBy(id = "downloadButton")
    private WebElement downloadButton;
    @FindBy(id = "uploadFile")
    private WebElement uploadFile;
    @FindBy(id = "uploadedFilePath")
    private WebElement uploadedFilePath;

    public UploadDownloadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Click on the download button")
    public void clickDownloadButton() {
        clickJavascriptExecutor11(downloadButton);
    }

    @Step("Check and delete downloaded file from folder")
    public void checkAndDeleteDownloadedFile() {
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

    @Step("send the download file from path and assert text on screen")
    public void sendPathAndAssertTextOnScreen(String ext) {
        Path tempFile = generateTempFile(ext);
        uploadFile.sendKeys(tempFile.toString());
        Assertions.assertEquals("C:\\fakepath\\" + tempFile.getFileName(), uploadedFilePath.getText());
    }


    private void clickJavascriptExecutor11(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    } //TODO: перенести в BasePage
}
