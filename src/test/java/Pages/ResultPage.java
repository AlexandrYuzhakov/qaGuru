package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultPage {
    SelenideElement tableTitle = $("#example-modal-sizes-title-lg");
    SelenideElement outputTable = $(".table-responsive");

    public ResultPage checkTitle(String value) {
        tableTitle.shouldHave(text(value));
        return this;
    }

    public ResultPage checkResult(String key, String value) {
        outputTable.$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }
}
