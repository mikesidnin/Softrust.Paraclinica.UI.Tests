import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DirectrionFullfillmentTest {

    @Test
    @DisplayName("fillDirectionForm")
    void fillDirectionForm(){

        String ipAddress = "http://109.95.224.42:2165/",
                middlePath = "test2/directions;mkabId=2662334;tapId=2670514;backUrl=%2Ftap%2Fcard%2F;menuName=Вернуться%20в%20ТАП;moduleName=Тап/Laboratory/new?ticket=",
                ticket = "hSqZx4lN38o%2BhW7%2B92nmww4lbjTAuCEt96ZbaHdmJdqb%2BrEQR79h8tfX0fyVbK8VbzA2qIJxhnCDyI1Jt2BDhx8rkenU4HtA7EB6XXXRmpwUcDVupdn1o3gQT7Fa4V2qXC73gzg%2BIIZcfbdD%2BDiZphG8utN6Rmd6JkpCnzXqN0fAvwW9s7wzp60Lp7JBQtrcX4miN%2FwiEGyoIolfBncEmeWagyWCTbhIhXncHFlAdCuKJBK2Jzl3Go9NxXuy4uPeENKUzccPPqYtbflvU3FF06JRB3PX1y2i61v7PRQTl5U8fEl1pesEoYhgJywKd7LgZy2CDDnod1fGxM%2BFqwTFen6dB%2BM%3D&MkabId=2662334&TapId=2670514&DocPrvdId=347&MisUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2&ReturnUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2%2FSchedule&newStyle=1";

        String directionPurpose[] ={"1 - Профилактическое исследование","2 - Диагностическое исследование"};

        Random random = new Random();
        int seed, i;

        seed = random.nextInt(10);

        if (seed <= 10)
             {
                 i = 0;
             }
        else
             {
                 i = 1;
             }
        //-----------------------------------------------------------------------------------------------------------
        open(ipAddress + middlePath + ticket);


        $(byAttribute("placeholder","Цель исследования")).click();
        $(byAttribute("title","1 - Профилактическое исследование")).click();

        $(byAttribute("placeholder","Причина направления")).click();
        $(byAttribute("title","2 - Самообращение профилакт")).click();

        $(byAttribute("placeholder","МКБ-10")).click();
        $(byAttribute("title","A01.1 - Паратиф A")).click();

        $(byAttribute("formcontrolname","comment")).setValue("test");

        $(byAttribute("placeholder","Исследование")).setValue("флюоро").pressEnter();

        $(byAttribute("ng-reflect-message","A06.09.006 - Флюорография легк")).click();

        $(".cdk-overlay-container").click();
        $(".btn-blue-square:not(.ng-star-inserted)").click();

        $($x("//button[text()=' Сохранить ']")).click();


    }


}
