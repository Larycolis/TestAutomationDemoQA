import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoqaUITests {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final Faker faker = new Faker();

    private final String path = "https://demoqa.com/";

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(path);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {" test", "123test", "123", "i", "TEST", "$*^"})
    void fillTheFullNameFieldAndSubmitTest(String param) {
        driver.get("https://demoqa.com/text-box");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].remove();", driver.findElement(By.id("adplus-anchor")));
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys(param);
        //Actions actions = new Actions(driver);
        // actions.moveToElement(driver.findElement(By.id("submit"))).perform();
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submit")));
        //driver.findElement(By.id("submit")).submit();
        // webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        //actions.moveToElement(driver.findElement(By.id("name"))).perform();
        //driver.findElement(By.id("submit")).click();
        Assertions.assertEquals("Name:" + param, driver.findElement(By.id("name")).getText());
        //Actions actions = new Actions(driver);
        //actions.doubleClick().contextClick().build().perform();
    }
}
