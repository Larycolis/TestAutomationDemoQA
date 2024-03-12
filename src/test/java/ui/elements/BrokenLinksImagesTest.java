package ui.elements;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidSelectorException;
import org.opentest4j.AssertionFailedError;

@ExtendWith(AllureJunit5.class)
@Feature("Images and Links")
@Story("UI Tests")
public class BrokenLinksImagesTest extends BaseTest {
    private static final String IMAGE_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > img:nth-child(3)";
    private static final String BROKEN_IMAGE_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > img:nth-child(7)";
    private static final String LINK_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > a:nth-child(11)";
    private static final String BROKEN_LINK_VALUE = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > a:nth-child(15)";
    private final String invalidPath = "https://the-internet.herokuapp.com/status_codes/500";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/broken");
    }

    @Test
    @DisplayName("The image is displayed on the page")
    void checkValidImageIsDisplayedTest() {
        CompareImageSize();
    }

    @Test()
    @DisplayName("The image is not displayed on the page")
    void checkInvalidImageIsDisplayedLikeAnBrokenImageIconTest() {
        CompareIconSizesAndThrowExceptionIfSizesIsMatches();
    }

    @Test
    @DisplayName("Click the link and switch to the valid page")
    void clickValidLinkAndCheckRelevantPageOpenedTest() {
        String path = "https://demoqa.com/";
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector(LINK_VALUE)));
        WaitUntilURLContainsThePath(path);
        CheckURLs(path);
    }

    //TODO: needs to be changed using a proxy
    @Test
    @DisplayName("Click the invalid link and switch to the information page")
    void clickInvalidLinkAndCheckRelevantPageNotOpenedTest() {
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector(BROKEN_LINK_VALUE)));
        WaitUntilURLContainsThePath(invalidPath);
        checkEqualsExpAndActText("""
                        This page returned a 500 status code.

                        For a definition and common list of HTTP status codes, go here""",
                By.cssSelector("#content > div > p"));
    }

    //TODO: needs to be changed using a proxy
    @Test
    @DisplayName("Click the invalid link and checking for throwing the AssertionFailedError")
    void clickInvalidLinkAndCheckServerErrorPageOpenedTest() {
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector(BROKEN_LINK_VALUE)));
        CompareURLsAndThrowExceptionIfTheURLsMatch();
    }

    @Step("Compare the sizes of expected and actual images")
    private void CompareImageSize() {
        Assertions.assertEquals(new Dimension(347, 100), getDriver().findElement(By.cssSelector(IMAGE_VALUE)).getSize());
    }

    @Step("Compare icon sizes and throw an exception if the sizes match 16x16")
    private void CompareIconSizesAndThrowExceptionIfSizesIsMatches() {
        Assertions.assertThrows(InvalidSelectorException.class,
                () -> Assertions.assertNotEquals(new Dimension(16, 16),
                        getDriver().findElement(By.xpath(BROKEN_IMAGE_VALUE)).getSize()));
    }

    @Step("Compare URLs and throw an exception if the URLs match")
    private void CompareURLsAndThrowExceptionIfTheURLsMatch() {
        Assertions.assertThrows(AssertionFailedError.class,
                () -> Assertions.assertNotEquals(invalidPath, getDriver().getCurrentUrl()));
    }

    @Step("Checking that the text is equals")
    private void CheckURLs(String path) {
        Assertions.assertEquals(path, getDriver().getCurrentUrl());
    }

    @Step("Checking that the text is equals")
    private void checkEqualsExpAndActText(String expText, By selector) {
        Assertions.assertEquals(expText, getDriver().findElement(selector).getText());
    }
}
