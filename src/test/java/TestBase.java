import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;
import static helpers.Environment.*;



public class TestBase {

    static String directionUrl;

    @BeforeAll
    @Step("Tests setup")
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        configureSelenide();
    }

    @BeforeEach
    public void BeforeEachTest(){
        Configuration.startMaximized = true;

        //----Если настройка Jenkins пустая, то берем дефолтные значения + дебаг и тестирование-------------------------
        mkabId = (mkabId == null) ? Config.mkabDefault : mkabId;
        tapId = (tapId == null) ? Config.tapDefault : tapId;
        docPrvdId = (docPrvdId == null) ? Config.docPrvdDefault : docPrvdId;
        ipAdress = (ipAdress == null) ? Config.ipAdressDefault : ipAdress;
        namespace = (namespace == null) ? Config.namespaceDefault  : namespace;
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach(){
        String sessionId = getSessionId();
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        closeWebDriver();
        if (isVideoOn) attachVideo(sessionId);
    }
}
