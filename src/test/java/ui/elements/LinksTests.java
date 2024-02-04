package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LinksTests extends BaseTest {
    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/links");
    }

    @ParameterizedTest
    @ValueSource(strings = {"simpleLink", "dynamicLink"})
    void clickLinksAndCheckRelevantPageOpenedTest(String locator) {
        getDriver().findElement(By.id(locator)).click();
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[1].toString());
        getWebDriverWait().until(ExpectedConditions.urlContains("https://demoqa.com/"));
    }

    //TODO: needs to be changed using a proxy
    @ParameterizedTest
    @CsvSource({"201, Created, created", "204, No Content, no-content",
            "301, Moved Permanently, moved", "400, Bad Request, bad-request",
            "401, Unauthorized, unauthorized", "403, Forbidden, forbidden",
            "404, Not Found, invalid-url"})
    void clickLinksAndCheckSelectedTest(int status, String text, String id) {
        String expectedLinkResponse = "Link has responded with staus " + status + " and status text " + text;
        String linkResponse = "linkResponse";
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", getDriver().findElement(By.id(id)));
        getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.id(linkResponse)));
        Assertions.assertEquals(expectedLinkResponse, getDriver().findElement(By.id(linkResponse)).getText());
    }
}
