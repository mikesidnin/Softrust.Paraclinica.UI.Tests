import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.Condition.*;
import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Owner("Mikhail Sidnin")
@Feature("Направления на исследование.")
class DirectionFulfillmentTest extends TestBase {

    @Test
    @Tag("web")
    @DisplayName("Создание направления на исследование.")
    void fillDirectionForm(){

        //----Если настройка Jenkins пустая, то берем дефолтные значения + дебаг и тестирование-------------------------
        if (mkabId == null || tapId == null || docPrvdId == null){
            mkabId = "2662334";
            tapId = "2670620";
            docPrvdId = "347";
        }

        step("Открываем случай лечения, заполняем жалобы, сохраняем ТАП.", () -> {

            openURLWithMkabTap(mkabId, tapId, docPrvdId);
            sleep(3000);

            complaints.click();
            pickComplaints.click();
            addComplaints.click();
            saveTapButton.click();
            snackbar.shouldBe(visible);
            String snackbarText = snackbar.getText();
            System.out.println(snackbarText);
            assertTrue(snackbarText.contains("Случай лечения"));
            assertTrue(snackbarText.contains("сохранен"));
        });

        step("Создаем направление на исследование из случая лечения.", () -> {

            createDirectionButton.scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
            directionType.click();
            pickLaboratoryType.click();
            createDirectionButton.click();
            snackbar.shouldBe(visible);
            String snackbarText = snackbar.getText();
            System.out.println(snackbarText);
            assertTrue(snackbarText.contains("Случай лечения"));
            assertTrue(snackbarText.contains("сохранен"));
        });

        step("Заполняем форму направления.", () -> {

            directionPurpose.click();
            dirPurCase1.click();
            directionReason.click();
            dirReasonCase1.click();
            mkb.click();
            diagnosis.click();
            comment.setValue("test");
            researchName.setValue("флюоро").pressEnter();
            researchFound.click();
            overlay.click();
            addResearchButton.click();
            saveDirectionButton.click();
            snackbar.shouldBe(visible);
            String snackbarText = snackbar.getText();
            System.out.println(snackbarText);
            assertTrue(snackbarText.contains("Направление"));
            assertTrue(snackbarText.contains("успешно создано"));
            body.shouldHave(text("Черновик"));
        });




    }
}
