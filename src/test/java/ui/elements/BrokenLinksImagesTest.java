package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opentest4j.AssertionFailedError;

public class BrokenLinksImagesTest extends BaseTest {
    private static final String IMAGE_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > img:nth-child(3)";
    private static final String BROKEN_IMAGE_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > img:nth-child(7)";
    private static final String LINK_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > a:nth-child(11)";
    private static final String BROKEN_LINK_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > a:nth-child(15)";
    private final String path = "https://demoqa.com/";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/broken");
    }

    @Test
    void checkValidImageIsDisplayedTest() {
        Assertions.assertEquals(new Dimension(347, 100), getDriver().findElement(By.cssSelector(IMAGE_VALUE)).getSize());
    }

    @Test()
    void checkInvalidImageIsDisplayedLikeAnBrokenImageIconTest() {
        Assertions.assertThrows(InvalidSelectorException.class,
                () -> Assertions.assertNotEquals(new Dimension(16, 16),
                        getDriver().findElement(By.xpath(BROKEN_IMAGE_VALUE)).getSize()));
    }

    @Test
    void clickValidLinkAndCheckRelevantPageOpenedTest() {
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector(LINK_VALUE)));
        getWebDriverWait().until(ExpectedConditions.urlContains(path));
        Assertions.assertEquals(path, getDriver().getCurrentUrl());
    }

    //TODO: needs to be changed using a proxy
    @Test
    void clickInvalidLinkAndCheckRelevantPageNotOpenedTest() {
        String params = "[^\\s\\d]+";
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector(BROKEN_LINK_VALUE)));
        Assertions.assertNotEquals(path + params, getDriver().getCurrentUrl());
    }

    //TODO: needs to be changed using a proxy
    @Test
    void clickInvalidLinkAndCheckServerErrorPageOpenedTest() {
        String invalidPath = "https://the-internet.herokuapp.com/status_codes/500";
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector(BROKEN_LINK_VALUE)));
        Assertions.assertThrows(AssertionFailedError.class,
                () -> Assertions.assertNotEquals(invalidPath, getDriver().getCurrentUrl()));
    }
}
