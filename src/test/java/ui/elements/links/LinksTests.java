package ui.elements.links;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LinksTests {
    private WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/links");
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"simpleLink", "dynamicLink"})
    void clickLinksAndCheckRelevantPageOpenedTest(String locator) {
        driver.findElement(By.id(locator)).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        webDriverWait.until(ExpectedConditions.urlContains("https://demoqa.com/"));
    }

    @ParameterizedTest
    @CsvSource({"201, Created, created", "204, No Content, no-content",
            "301, Moved Permanently, moved", "400, Bad Request, bad-request",
            "401, Unauthorized, unauthorized", "403, Forbidden, forbidden",
            "404, Not Found, invalid-url"})
    void clickLinksAndCheckSelectedTest(int status, String text, String id) {
        String expectedLinkResponse = "Link has responded with staus " + status + " and status text " + text;
        String linkResponse = "linkResponse";
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(linkResponse)));
        Assertions.assertEquals(expectedLinkResponse, driver.findElement(By.id(linkResponse)).getText());
    }
}
