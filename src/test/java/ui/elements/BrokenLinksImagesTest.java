package ui.elements;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.page.elements.BrokenLinksImagesPage;

@ExtendWith(AllureJunit5.class)
@Feature("Images and Links")
@Story("UI Tests")
public class BrokenLinksImagesTest extends BaseTest {
    BrokenLinksImagesPage brokenLinksImagesPage;

    @BeforeEach
    void setUp() {
        brokenLinksImagesPage = new BrokenLinksImagesPage(getDriver());
        getDriver().get("https://demoqa.com/broken");
    }

    @Test
    @DisplayName("The image is displayed on the page")
    void checkValidImageIsDisplayedTest() {
        brokenLinksImagesPage.compareImageSize();
    }

    @Test()
    @DisplayName("The image is not displayed on the page")
    void checkInvalidImageIsDisplayedLikeAnBrokenImageIconTest() {
        brokenLinksImagesPage.compareIconSizesAndThrowExceptionIfSizesIsMatches();
    }

    @Test
    @DisplayName("Click the link and switch to the valid page")
    void clickValidLinkAndCheckRelevantPageOpenedTest() {
        brokenLinksImagesPage.clickValidLinkWaitAndCheckURL();
    }

    //TODO: needs to be changed using a proxy
    @Test
    @DisplayName("Click the invalid link and switch to the information page")
    void clickInvalidLinkAndCheckRelevantPageNotOpenedTest() {
        brokenLinksImagesPage.clickBrokenLinkWaitAndCheckBrokenLinkPageBody();
    }

    //TODO: needs to be changed using a proxy
    @Test
    @DisplayName("Click the invalid link and checking for throwing the AssertionFailedError")
    void clickInvalidLinkAndCheckServerErrorPageOpenedTest() {
        brokenLinksImagesPage.clickBrokenLinkWaitCompareUrlsAndThrowExceptionIfTheURLsMatch();
    }
}
