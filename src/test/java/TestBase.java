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

    static String directionUrl;

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
                    researchFound = $(By.xpath("(//div[@class='mat-checkbox-inner-container'])[3]"));

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
            tapId = "2670990";
            docPrvdId = "2521";
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
                ticket = "ticket=D9VQnls2TN%2B2s%2FwzBNicUCtcrH1JNeDL6%2BRSxXP6jeJ%2FhYi6e%2FnGu13NyHHvOVBmmP%2BETKS%2FoKu%2FQraiIvDFoVWFUFZEhXMbiAauqiPVXFVP6vTnUOFTt49dWUKrLJu9qQ9jKZrXXXi%2Fv6VkaxQMVqcjfkV2ctNH5UXIdnUymK2FwDrwrUpwEtwKG9yrqvOnTwFM7NNxX%2BzH3lZd1sKNgRRnk1M4GqLT3uJFQ0Tkif%2BxaflrVRtMqRMen58nmCVjM%2FL0b4dFxdL7Yvlbyb5OvlP2qnf%2F5yfz9%2BfhQXSjiK5NMlmnYlwlEiae%2BhdLY2jxvxjjknDJwxIXxmrRvbt7jq1thpE%3D",
                mkabIdPart = "MkabId=" + mkabID,
                tapIdPart = "TapId=" + tapID,
                docPrvdIdPart = "DocPrvdId=" + docPrvdId,
                misUrl = "MisUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2",
                returnUrl = "ReturnUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2%2FSchedule";

        open(ipAddress + relativePath + "?" + ticket+ "&" + mkabIdPart + "&" + tapIdPart + "&" + docPrvdIdPart + "&" + misUrl + "&" + returnUrl);
    }


}
