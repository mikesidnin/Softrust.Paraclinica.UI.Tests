import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;
import static helpers.Environment.*;
import static helpers.LoadCredentials.getCredentialsFromJson;
import static io.qameta.allure.Allure.parameter;


public class TestBase {
//----Локаторы-------------------------------------------------------------------------------------------------------

    //----Случай лечения---------------------------------------------------------------------------------------------
    SelenideElement complaints = $(byText("Жалобы")),
                    pickComplaints = $(By.xpath("//span[@class='complaintTag ng-star-inserted']")),
                    addComplaints = $(By.xpath("//button[@class='btn-blue-square add-complaint']")),
                    saveTapButton = $(By.xpath("(//button[text()=' Сохранить '])[2]")),
                    snackbar = $(By.xpath(".//simple-snack-bar[@class='mat-simple-snackbar ng-star-inserted']/span")),
                    createDirectionButton = $(byText("Создать")),
                    directionType = $(By.xpath("(//div[@class='mat-select-value'])[2]")),
                    pickLaboratoryType = $(By.xpath("(//mat-option[@class='mat-option ng-star-inserted']//span)[3]")),
                    deleteDirectionJournalButton = $(By.xpath("//i[text()=' delete ']"));

    //----Контрол цели исследования----------------------------------------------------------------------------------
    SelenideElement directionPurpose = $(byAttribute("placeholder","Цель исследования")),
                    dirPurCase1 =  $(byAttribute("title","1 - Профилактическое исследование")),
                    dirPurCase2 =  $(byAttribute("title","2 - Диагностическое исследование"));

    //----Контрол цели исследования----------------------------------------------------------------------------------
    SelenideElement directionReason = $(byAttribute("placeholder","Причина направления")),
                    dirReasonCase1 =  $(byAttribute("title","1 - Самообращение по жалобе")),
                    dirReasonCase2 =  $(byAttribute("title","2 - Самообращение профилакт"));

    //----Контрол диагнозов-----------------------------------------------------------------------------------------
    SelenideElement mkb = $(byAttribute("placeholder","МКБ-10")),
                    diagnosis = $(byAttribute("title","A01.1 - Паратиф A"));

    //----Поле для комментария---------------------------------------------------------------------------------------
    SelenideElement comment = $(byAttribute("formcontrolname","comment"));

    //----Исследования-----------------------------------------------------------------------------------------------
    SelenideElement researchName = $(byAttribute("placeholder","Исследование")),
                    researchFound = $(byAttribute("ng-reflect-message","A06.09.006 - Флюорография легк"));

    //----Оверлей (служебный)----------------------------------------------------------------------------------------
    SelenideElement overlay = $(".cdk-overlay-container"),
                    body = $("body");

    //----Кнопки-----------------------------------------------------------------------------------------------------
    SelenideElement addResearchButton = $(".btn-blue-square:not(.ng-star-inserted)"),
                    saveDirectionButton = $($x("//button[text()=' Сохранить ']")),
                    deleteDirectionButton = $(By.xpath("//button[text()='Удалить']"));

//-------------------------------------------------------------------------------------------------------------------
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
        if (mkabId == null || tapId == null || docPrvdId == null) {
            mkabId = "2662334";
            tapId = "2670620";
            docPrvdId = "347";
        }
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

    //----Собираем УРЛ белого случая лечения---------------------------------------------------------------------
    public void openURLWithMkabTap(String mkabID, String tapID, String docPrvdId) {

        String ipAddress = "http://109.95.224.42:2165/", //"http://109.95.224.42:2165/",
                relativePath = "test2/tap/card",
                ticket = "ticket=IhNpmO2lhSwbyJ1orHAD7qLggeE9S95511DXiMdsj3w5k220ljbVUxm0dip%2Bqupr7EaWXDIx%2BAIMTpb9cbtswnOPFJaPhIflTvaM2%2FYsk5CXrCvG6DgJpRgn4geoCNscGgSXZmR8J%2FcnGhMmxb3Z05OafJ51%2B2vDddXbjucEe9XjEw0PkUPz7pru5I7gM7vMz6lIbjDiV4g3fZiYD8EvODcDDANWXziHQjTrPhyhR0x64QC7d4iitOPGni%2Bg38kAvW6BGahH%2BVi9r6NUidg8rTxB36taAgHVFT01eAkjf%2BMbSFNOl%2BKT0CucPKjw%2BD6mJgbunKwaDvQiEXclRDrcMrkw9jc%3D",
                mkabIdPart = "MkabId=" + mkabID,
                tapIdPart = "TapId=" + tapID,
                docPrvdIdPart = "DocPrvdId=" + docPrvdId,
                misUrl = "MisUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2",
                returnUrl = "ReturnUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2%2FSchedule";

        open(ipAddress + relativePath + "?" + ticket+ "&" + mkabIdPart + "&" + tapIdPart + "&" + docPrvdIdPart + "&" + misUrl + "&" + returnUrl);
    }
/*
    public void getDirectionNumber(final String number) {
        parameter("number", number);

        String baseUrl = "http://109.95.224.42:2165/";
        String baseUrlPath = "test2/paraclinic-api/api/v2/direction/";
        String ticket = "IhNpmO2lhSwbyJ1orHAD7qLggeE9S95511DXiMdsj3w5k220ljbVUxm0dip+qupr7EaWXDIx+AIMTpb9cbtswnOPFJaPhIflTvaM2/Ysk5CXrCvG6DgJpRgn4geoCNscGgSXZmR8J/cnGhMmxb3Z05OafJ51+2vDddXbjucEe9XjEw0PkUPz7pru5I7gM7vMz6lIbjDiV4g3fZiYD8EvODcDDANWXziHQjTrPhyhR0x64QC7d4iitOPGni+g38kAvW6BGahH+Vi9r6NUidg8rTxB36taAgHVFT01eAkjf+MbSFNOl+KT0CucPKjw+D6mJgbunKwaDvQiEXclRDrcMrkw9jc=";

        Issue issue = new Issue();
        issue = (Issue) given()
                .filter(new AllureRestAssured())
                .header("Ticket", ticket)
                .baseUrl(baseUrl)
                .when()
                .get(baseUrlPath + number)
                .then()
                .statusCode(200)
                .body("number", equalTo(ISSUE_NUMBER))
                .body("title", equalTo(ISSUE_TITLE))
                .body("body",equalTo(ISSUE_TEXT));
    }
*/


}
