package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.page.elements.TextboxPage;

public class TextboxTests extends BaseTest {
    private TextboxPage textboxPage;

    @BeforeEach
    void setUp() {
        textboxPage = new TextboxPage(getDriver());
        getDriver().get("https://demoqa.com/text-box");
    }

    @ParameterizedTest
    @ValueSource(strings = {" test", "123test", "123", "i", "TEST", "$*^"})
    void fillTheFullNameFieldAndSubmitTest(String fullName) {
        textboxPage.sendUserName(fullName);
        textboxPage.clickSubmit();
        textboxPage.assertNameTextOnTheScreen(fullName);
    }

    @ParameterizedTest
    @CsvSource({"Ivan Ivanovich, test@test.ts, Gorkog 1, Lenin 12"})
    void fillTheFormAndSubmitTest(String fullName, String email, String currentAddress, String permanentAddress) {
        textboxPage.sendUserName(fullName);
        textboxPage.sendEmail(email);
        textboxPage.sendCurrentAddress(currentAddress);
        textboxPage.sendPermanentAddress(permanentAddress);
        textboxPage.clickSubmit();
        textboxPage.assertNameTextOnTheScreen(fullName);
        textboxPage.assertEmailTextOnTheScreen(email);
        textboxPage.assertCurrentAddressTextOnTheScreen(currentAddress);
        textboxPage.assertPermanentAddressTextOnTheScreen(permanentAddress);
    }
}
