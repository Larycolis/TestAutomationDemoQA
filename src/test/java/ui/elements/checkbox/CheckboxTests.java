package ui.elements.checkbox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckboxTests {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String path = "https://demoqa.com/checkbox";
        driver.get(path);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
    @Test
    void clickCheckboxesAndCheckSelected() {
        driver.findElement(By.cssSelector("button[aria-label='Expand all']")).click();
        driver.findElement(By.cssSelector("[for='tree-node-home'] span.rct-checkbox")).click();
        List<WebElement> webElements = driver.findElements(By.cssSelector("input[type='checkbox']"));
        List<WebElement> webElementsText = driver.findElements(By.className("text-success"));
        List<String> actualTitles = driver.findElements(By.cssSelector("span.rct-title")).stream()
                .map(el -> el.getText().replaceAll(".doc", "").replaceAll(" ", "").toLowerCase())
                .toList();
        List<String> expectedTitles = webElementsText.stream()
                .map(el -> el.getText().toLowerCase())
                .toList();
        System.out.println(expectedTitles);
        System.out.println(actualTitles);
        Assertions.assertEquals(expectedTitles, actualTitles);
        Assertions.assertEquals(17, webElements.size());
        for (WebElement tmp : webElements) {
            Assertions.assertTrue(tmp.isSelected());
        }
    }
}
