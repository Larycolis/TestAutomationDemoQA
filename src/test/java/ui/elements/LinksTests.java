package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.page.elements.LinksPage;

public class LinksTests extends BaseTest {
    private LinksPage linksPage;

    @BeforeEach
    void setUp() {
        linksPage = new LinksPage(getDriver());
        getDriver().get("https://demoqa.com/links");
    }

    @ParameterizedTest
    @ValueSource(strings = {"simpleLink", "dynamicLink"})
    @DisplayName(" ")
    void clickLinksAndCheckRelevantPageOpenedTest(String locator) {
        linksPage.clickSwitchToNewWindowAndCheckUrl(locator);
    }

    //TODO: needs to be changed using a proxy
    @ParameterizedTest
    @CsvSource({"201, Created, created", "204, No Content, no-content",
            "301, Moved Permanently, moved", "400, Bad Request, bad-request",
            "401, Unauthorized, unauthorized", "403, Forbidden, forbidden",
            "404, Not Found, invalid-url"})
    @DisplayName("Checking the api response for link click")
    void clickLinksAndCheckSelectedTest(int status, String text, String id) {
        LinksPage.Result result = linksPage.clickTheLinkAndGetValidText(status, text, id);
        Assertions.assertEquals(result.expectedLinkResponse(), getDriver().findElement(By.id(result.linkResponse())).getText());
    }
}
