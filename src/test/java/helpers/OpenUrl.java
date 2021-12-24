package helpers;

import static com.codeborne.selenide.Selenide.open;
import static helpers.Environment.*;

public class OpenUrl {
    //----Собираем УРЛ белого случая лечения---------------------------------------------------------------------
    public static void openTapCard() {

        String ipAddress = ipAdress,
                relativePath = namespace + "",
                ticket = "",
                mkabIdPart = "MkabId=" + mkabId,
                tapIdPart = "TapId=" + tapId,
                docPrvdIdPart = "DocPrvdId=" + docPrvdId,
                misUrl = "",
                returnUrl = "";

        open(ipAddress + relativePath + "?" + ticket+ "&" + mkabIdPart + "&" + tapIdPart + "&" + docPrvdIdPart + "&" + misUrl + "&" + returnUrl);
    }

}
