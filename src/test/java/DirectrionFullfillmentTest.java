import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DirectrionFullfillmentTest {

    @Test
    @DisplayName("fillDirectionForm")
    void fillDirectionForm(){

        String ipAddress = "http://109.95.224.42:2165/",
                middlePath = "test2/directions;mkabId=2662334;tapId=2670514;backUrl=%2Ftap%2Fcard%2F;menuName=Вернуться%20в%20ТАП;moduleName=Тап/Laboratory/new?ticket=",
                ticket = "hSqZx4lN38o%2BhW7%2B92nmww4lbjTAuCEt96ZbaHdmJdqb%2BrEQR79h8tfX0fyVbK8VbzA2qIJxhnCDyI1Jt2BDhx8rkenU4HtA7EB6XXXRmpwUcDVupdn1o3gQT7Fa4V2qXC73gzg%2BIIZcfbdD%2BDiZphG8utN6Rmd6JkpCnzXqN0fAvwW9s7wzp60Lp7JBQtrcX4miN%2FwiEGyoIolfBncEmeWagyWCTbhIhXncHFlAdCuKJBK2Jzl3Go9NxXuy4uPeENKUzccPPqYtbflvU3FF06JRB3PX1y2i61v7PRQTl5U8fEl1pesEoYhgJywKd7LgZy2CDDnod1fGxM%2BFqwTFen6dB%2BM%3D&MkabId=2662334&TapId=2670514&DocPrvdId=347&MisUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2&ReturnUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2%2FSchedule&newStyle=1";

        open(ipAddress + middlePath + ticket);

        //$("#mat-input-186").val("про").pressEnter();

        //$("[ng-reflect-placeholder='Цель исследования']").val("acsaswwqf");
        //$x("//input[@class='mat-input-element']").click();


        $(byAttribute("placeholder","Цель исследования")).click();
        $("#mcdk-overlay-2").click();
        $(byAttribute("class","mat-option-ripple")).click();


        //$x("//input[@id='mat-checkbox-1-input']").click();

        $(byText("Цель исследования")).val("asc");
        $(byClassName("custom-check-box")).click();

    }


}
