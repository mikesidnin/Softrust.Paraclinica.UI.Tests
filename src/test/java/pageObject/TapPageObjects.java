package pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TapPageObjects {
    //----Локаторы-------------------------------------------------------------------------------------------------------

    //----Случай лечения---------------------------------------------------------------------------------------------
    public static SelenideElement complaints = $(byText("Жалобы")),
            pickComplaints = $(By.xpath("//span[@class='complaintTag ng-star-inserted']")),
            addComplaints = $(By.xpath("//button[@class='btn-blue-square add-complaint']")),
            saveTapButton = $(By.xpath("(//button[text()=' Сохранить '])[2]")),
            snackbar = $(By.xpath(".//simple-snack-bar[@class='mat-simple-snackbar ng-star-inserted']/span")),
            createDirectionButton = $(byText("Создать")),
            directionType = $(By.xpath("(//div[@class='mat-select-value'])[2]")),
            pickLaboratoryType = $(By.xpath("(//mat-option[@class='mat-option ng-star-inserted']//span)[3]")),
            deleteDirectionJournalButton = $(By.xpath("//i[text()=' delete ']"));

    //----Контрол цели исследования----------------------------------------------------------------------------------
    public static SelenideElement directionPurpose = $(byAttribute("placeholder","Цель исследования")),
            dirPurCase1 =  $(byAttribute("title","1 - Профилактическое исследование")),
            dirPurCase2 =  $(byAttribute("title","2 - Диагностическое исследование"));

    //----Контрол цели исследования----------------------------------------------------------------------------------
    public static SelenideElement directionReason = $(byAttribute("placeholder","Причина направления")),
            dirReasonCase1 =  $(byAttribute("title","1 - Самообращение по жалобе")),
            dirReasonCase2 =  $(byAttribute("title","2 - Самообращение профилакт"));

    //----Контрол диагнозов-----------------------------------------------------------------------------------------
    public static SelenideElement mkb = $(byAttribute("placeholder","МКБ-10")),
            diagnosis = $(byAttribute("title","A01.1 - Паратиф A"));

    //----Поле для комментария---------------------------------------------------------------------------------------
    public static SelenideElement comment = $(byAttribute("formcontrolname","comment"));

    //----Исследования-----------------------------------------------------------------------------------------------
    public static SelenideElement researchName = $(byAttribute("placeholder","Исследование")),
            researchFound = $(By.xpath("(//div[@class='mat-checkbox-inner-container'])[3]"));

    //----Оверлей (служебный)----------------------------------------------------------------------------------------
    public static SelenideElement overlay = $(".cdk-overlay-container"),
            body = $("body");

    //----Кнопки-----------------------------------------------------------------------------------------------------
    public static SelenideElement addResearchButton = $(".btn-blue-square:not(.ng-star-inserted)"),
            saveDirectionButton = $($x("//button[text()=' Сохранить ']")),
            deleteDirectionButton = $(By.xpath("//button[text()='Удалить']"));

//-------------------------------------------------------------------------------------------------------------------
}
