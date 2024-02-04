package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opentest4j.AssertionFailedError;

public class BrokenLinksImagesTest extends BaseTest {
    private static final String XPATH_IMAGE = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/img[1]";
    private static final String XPATH_BROKEN_IMAGE = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/img[2]";
    private static final String XPATH_LINK = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/a[1]";
    private static final String XPATH_BROKEN_LINK = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/a[2]";
    private final String path = "https://demoqa.com/";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/broken");
    }

    @Test
    void checkValidImageIsDisplayedTest() {
        Assertions.assertEquals(new Dimension(347, 100), getDriver().findElement(By.xpath(XPATH_IMAGE)).getSize());
    }

    @Test()
    void checkInvalidImageIsDisplayedLikeAnBrokenImageIconTest() {
        Assertions.assertThrows(AssertionFailedError.class,
                () -> Assertions.assertNotEquals(new Dimension(16, 16),
                        getDriver().findElement(By.xpath(XPATH_BROKEN_IMAGE)).getSize()));
    }

    @Test
    void clickValidLinkAndCheckRelevantPageOpenedTest() {
        clickJavascriptExecutor(getDriver().findElement(By.xpath(XPATH_LINK)));
        getWebDriverWait().until(ExpectedConditions.urlContains(path));
        Assertions.assertEquals(path, getDriver().getCurrentUrl());
    }

    //TODO: needs to be changed using a proxy
    @Test
    void clickInvalidLinkAndCheckRelevantPageNotOpenedTest() {
        String params = "[^\\s\\d]+";
        clickJavascriptExecutor(getDriver().findElement(By.xpath(XPATH_BROKEN_LINK)));
        Assertions.assertNotEquals(path + params, getDriver().getCurrentUrl());
    }

    //TODO: needs to be changed using a proxy
    @Test
    void clickInvalidLinkAndCheckServerErrorPageOpenedTest() {
        String invalidPath = "https://the-internet.herokuapp.com/status_codes/500";
        clickJavascriptExecutor(getDriver().findElement(By.xpath(XPATH_BROKEN_LINK)));
        Assertions.assertThrows(AssertionFailedError.class,
                () -> Assertions.assertNotEquals(invalidPath, getDriver().getCurrentUrl()));
    }
}
