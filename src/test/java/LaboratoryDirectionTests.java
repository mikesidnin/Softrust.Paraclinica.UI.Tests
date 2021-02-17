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
class LaboratoryDirectionTests extends TestBase {

    @Test
    @Tag("web")
    @DisplayName("Создание направления на исследование.")
    void fillDirectionForm() {

        step("Открываем случай лечения, заполняем жалобы, сохраняем ТАП.", () -> {
            openURLWithMkabTap(mkabId, tapId, docPrvdId);
            sleep(3000);

            complaints.click();
            pickComplaints.click();
            addComplaints.click();
            saveTapButton.click();

            snackbar.shouldBe(visible);
            String snackbarTapSuccess = snackbar.getText();
            System.out.println(snackbarTapSuccess);
            assertTrue(snackbarTapSuccess.contains("Случай лечения"));
            assertTrue(snackbarTapSuccess.contains("сохранен"));
        });

        step("Создаем направление на исследование из случая лечения.", () -> {
            sleep(3000);

            createDirectionButton.scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
            directionType.click();
            pickLaboratoryType.click();
            createDirectionButton.click();

            snackbar.shouldBe(visible);
            String snackbarTapSuccess = snackbar.getText();
            System.out.println(snackbarTapSuccess);
            assertTrue(snackbarTapSuccess.contains("Случай лечения"));
            assertTrue(snackbarTapSuccess.contains("сохранен"));
        });

        step("Заполняем форму направления.", () -> {
            sleep(3000);

            directionPurpose.click();
            dirPurCase1.click();
            directionReason.click();
            dirReasonCase1.click();
            mkb.click();
            diagnosis.click();
            comment.setValue("test");
            researchName.setValue("A06.09.006.001").pressEnter();
            sleep(1000);

            researchFound.click();
            overlay.click();
            addResearchButton.click();
            saveDirectionButton.click();

            snackbar.shouldBe(visible);
            String snackbarDirectionCreateSuccess = snackbar.getText();
            System.out.println(snackbarDirectionCreateSuccess);
            assertTrue(snackbarDirectionCreateSuccess.contains("Направление"));
            assertTrue(snackbarDirectionCreateSuccess.contains("успешно создано"));
            body.shouldHave(text("Черновик"));
        });
    }

    @Test
    @Tag("web")
    @DisplayName("Удаление черновика исследования.")
    void deleteDirection(){

        step("Удаление черновика из журнала направлений.", () -> {

            openURLWithMkabTap(mkabId, tapId, docPrvdId);
            sleep(3000);

            createDirectionButton.scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
            deleteDirectionJournalButton.click();

            snackbar.shouldBe(visible);
            String snackbarText = snackbar.getText();
            System.out.println(snackbarText);
            assertTrue(snackbarText.contains("Удаление"));
            assertTrue(snackbarText.contains("успешно"));
        });

        step("Удаление черновика направлений из интерфейса направления.", () -> {
            sleep(5000);
            createDirectionButton.scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
            directionType.click();
            pickLaboratoryType.click();
            createDirectionButton.click();

            snackbar.shouldBe(visible);
            String snackbarTapSuccess = snackbar.getText();
            System.out.println(snackbarTapSuccess);
            assertTrue(snackbarTapSuccess.contains("Случай лечения"));
            assertTrue(snackbarTapSuccess.contains("сохранен"));

            sleep(4000);

            directionPurpose.click();
            dirPurCase1.click();
            directionReason.click();
            dirReasonCase1.click();
            mkb.click();
            diagnosis.click();
            comment.setValue("deleted");
            researchName.setValue("A06.09.006.001").pressEnter();
            sleep(1000);

            researchFound.click();
            overlay.click();
            addResearchButton.click();
            saveDirectionButton.click();

            snackbar.shouldBe(visible);
            String snackbarDirectionCreateSuccess = snackbar.getText();
            System.out.println(snackbarDirectionCreateSuccess);
            assertTrue(snackbarDirectionCreateSuccess.contains("Направление"));
            assertTrue(snackbarDirectionCreateSuccess.contains("успешно создано"));

            sleep(5000);

            deleteDirectionButton.scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
            deleteDirectionButton.click();

            snackbar.shouldBe(visible);
            String snackbarDeleteDirectionSuccess = snackbar.getText();
            System.out.println(snackbarDeleteDirectionSuccess);
            assertTrue(snackbarDeleteDirectionSuccess.contains("Удаление направления"));
            assertTrue(snackbarDeleteDirectionSuccess.contains("успешно"));
        });
   }
}
