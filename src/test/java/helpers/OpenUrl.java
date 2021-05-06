package helpers;

import static com.codeborne.selenide.Selenide.open;
import static helpers.Environment.*;

public class OpenUrl {
    //----Собираем УРЛ белого случая лечения---------------------------------------------------------------------
    public static void openTapCard() {

        String ipAddress = ipAdress,
                relativePath = namespace + "/tap/card",
                ticket = "ticket=D9VQnls2TN%2B2s%2FwzBNicUCtcrH1JNeDL6%2BRSxXP6jeJ%2FhYi6e%2FnGu13NyHHvOVBmmP%2BETKS%2FoKu%2FQraiIvDFoVWFUFZEhXMbiAauqiPVXFVP6vTnUOFTt49dWUKrLJu9qQ9jKZrXXXi%2Fv6VkaxQMVqcjfkV2ctNH5UXIdnUymK2FwDrwrUpwEtwKG9yrqvOnTwFM7NNxX%2BzH3lZd1sKNgRRnk1M4GqLT3uJFQ0Tkif%2BxaflrVRtMqRMen58nmCVjM%2FL0b4dFxdL7Yvlbyb5OvlP2qnf%2F5yfz9%2BfhQXSjiK5NMlmnYlwlEiae%2BhdLY2jxvxjjknDJwxIXxmrRvbt7jq1thpE%3D",
                mkabIdPart = "MkabId=" + mkabId,
                tapIdPart = "TapId=" + tapId,
                docPrvdIdPart = "DocPrvdId=" + docPrvdId,
                misUrl = "MisUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2",
                returnUrl = "ReturnUrl=http:%2F%2F192.168.7.54%2Fmis%2Ftest2%2FSchedule";

        open(ipAddress + relativePath + "?" + ticket+ "&" + mkabIdPart + "&" + tapIdPart + "&" + docPrvdIdPart + "&" + misUrl + "&" + returnUrl);
    }

}
