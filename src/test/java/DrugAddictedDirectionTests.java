import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static helpers.OpenUrl.*;
import static pageObject.TapPageObjects.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Owner("Mikhail Sidnin")
@Feature("Направления на исследование.")
class DrugAddictedDirectionTests extends TestBase {

    @Test
    @Tag("web")
    @DisplayName("Создание направления на исследование.")
    void fillDirectionForm() {

        step("Открываем случай лечения, заполняем жалобы, сохраняем ТАП.", () -> {
            openTapCard();
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
            directionUrl = WebDriverRunner.url();

        });
    }

        @Test
        @Tag("web")
        @DisplayName("Создание направления на исследование.")
        void signDirectionForm() {

            step("Подписываем направление.", () -> {

                open(directionUrl);
                sleep(3000);

                $(By.xpath("//button[text()=' Подписать ']")).click();
            });
    }

    @Test
    @Tag("web")
    @DisplayName("Создание направления на исследование.")
    void anotherMeaningForm() {

        step("Вносим результаты.", () -> {

            open(directionUrl);
            sleep(3000);
            $(By.xpath("//div[@ng-reflect-klass='expand-button']//i[1]")).click();

            $(By.xpath("(//div[@class='mat-checkbox-inner-container'])[2]")).scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
            $(By.xpath("(//div[@class='mat-checkbox-inner-container'])[2]")).click();

            $(By.xpath("html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/main[1]/app-directions[1]/div[1]/diagnostic-research[1]/div[1]/research-block[1]/div[1]/div[2]/research-entry[1]/div[1]/div[2]/div[1]/app-research-result[1]/div[1]/div[2]/st-select[1]/mat-form-field[1]/div[1]/div[1]/div[1]")).click();
            $(By.xpath("//span[text()=' Норма ']")).click();

            $(By.xpath("(//div[@class='mat-checkbox-inner-container'])[3]")).scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
            $(By.xpath("(//div[@class='mat-checkbox-inner-container'])[3]")).click();

            saveDirectionButton.click();
        });

        step("Вносим результаты.", () -> {

            for (int i = 1; i<=5; i++){
                $(By.xpath("//div[@ng-reflect-klass='expand-button']//i[1]")).click();
                $(By.xpath("//button[text()=' Добавить ']")).click();
                $(By.xpath("html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/main[1]/app-directions[1]/div[1]/diagnostic-research[1]/div[1]/research-block[1]/div[1]/div[2]/research-entry[1]/div[1]/div[2]/div[1]/research-second-opinions[1]/div[1]/div[2]/second-opinion-entry[" + i + "]/div[1]/div[2]/div[1]/app-research-result[1]/div[1]/div[2]/st-select[1]/mat-form-field[1]/div[1]/div[1]/div[1]")).scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
                $(By.xpath("html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/main[1]/app-directions[1]/div[1]/diagnostic-research[1]/div[1]/research-block[1]/div[1]/div[2]/research-entry[1]/div[1]/div[2]/div[1]/research-second-opinions[1]/div[1]/div[2]/second-opinion-entry[" + i + "]/div[1]/div[2]/div[1]/app-research-result[1]/div[1]/div[2]/st-select[1]/mat-form-field[1]/div[1]/div[1]/div[1]")).click();
                $(By.xpath("//span[text()=' Норма ']")).click();

                $(By.cssSelector("html>body>app-root>ng-component>div>main>app-directions>div>diagnostic-research>div>research-block>div>div:nth-of-type(2)>research-entry>div>div:nth-of-type(2)>div>research-second-opinions>div>div:nth-of-type(2)>second-opinion-entry:nth-of-type(" + i + ")>div>div:nth-of-type(2)>div>div:nth-of-type(2)>mat-checkbox>label>div")).scrollIntoView(("{behavior: \"instant\", block: \"center\", inline: \"center\"}"));
                $(By.cssSelector("html>body>app-root>ng-component>div>main>app-directions>div>diagnostic-research>div>research-block>div>div:nth-of-type(2)>research-entry>div>div:nth-of-type(2)>div>research-second-opinions>div>div:nth-of-type(2)>second-opinion-entry:nth-of-type(" + i + ")>div>div:nth-of-type(2)>div>div:nth-of-type(2)>mat-checkbox>label>div")).click();
                saveDirectionButton.click();
            }

        });





    }



}

