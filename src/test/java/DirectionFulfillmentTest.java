import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Owner("Mikhail Sidnin")
@Feature("Заполнение и сохранение направления на исследование")
class DirectionFulfillmentTest extends TestBase {

    @Test
    @DisplayName("fillDirectionForm")
    void fillDirectionForm(){

        String mkabId = System.getProperty("mkaId");
        String tapId = System.getProperty("tapId");

        if (mkabId == null || tapId == null ){
            mkabId = "2662400";
            tapId = "2670594";
        }

        openURLWithMkabTap(mkabId,tapId);

        directionPurpose.click();
        dirPurCase1.click();

        directionReason.click();
        dirReasonCase1.click();

        //$(".mat-button").click();

        mkb.click();
        diagnosis.click();

        comment.setValue("test");

        researchName.setValue("флюоро").pressEnter();
        researchFound.click();

        overlay.click();
        buttonAdd.click();

        buttonSave.click();

        successMessage.shouldHave(text("Направление успешно сохранено")); // не работает

    }
}
