import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;
import static helpers.Environment.isVideoOn;


public class TestBase {
//----Локаторы-------------------------------------------------------------------------------------------------------

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
    SelenideElement overlay = $(".cdk-overlay-container");

    //----Кнопки-----------------------------------------------------------------------------------------------------
    SelenideElement add = $(".btn-blue-square:not(.ng-star-inserted)"),
                    save = $($x("//button[text()=' Сохранить ']"));

//-------------------------------------------------------------------------------------------------------------------
    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        configureSelenide();
    }

    @BeforeEach
    public void BeforeEachTest(){
        Configuration.startMaximized = true;
    }

    @AfterEach
    public void afterEach(){
        String sessionId = getSessionId();
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        closeWebDriver();
        if (isVideoOn) attachVideo(sessionId);
    }

    public void openURLWithMkabTap(String mkabID, String tapID) {
        String ipAddress = "http://109.95.224.42:2165/",
                relativePath = "test2/directions;",
                mkabIdPart = "mkabId=" + mkabID + ";",
                tapIdPart = "tapId=" + tapID + ";",
                backUrl = "backUrl=%2Ftap%2Fcard%2F;menuName=Вернуться%20в%20ТАП;moduleName=Тап/Laboratory/new?",
                ticket = "ticket=hSqZx4lN38o%2BhW7%2B92nmww4lbjTAuCEt96ZbaHdmJdqb%2BrEQR79h8tfX0fyVbK8VbzA2qIJxhnCDyI1Jt2BDhx8rkenU4HtA7EB6XXXRmpwUcDVupdn1o3gQT7Fa4V2qXC73gzg%2BIIZcfbdD%2BDiZphG8utN6Rmd6JkpCnzXqN0fAvwW9s7wzp60Lp7JBQtrcX4miN%2FwiEGyoIolfBncEmeWagyWCTbhIhXncHFlAdCuKJBK2Jzl3Go9NxXuy4uPeENKUzccPPqYtbflvU3FF06JRB3PX1y2i61v7PRQTl5U8fEl1pesEoYhgJywKd7LgZy2CDDnod1fGxM%2BFqwTFen6dB%2BM%3D&MkabId=2662334&TapId=2670514&DocPrvdId=347&MisUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2&ReturnUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2%2FSchedule&newStyle=1";

        open(ipAddress + relativePath + mkabIdPart + tapIdPart + backUrl + ticket);
    }



}
