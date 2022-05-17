import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class AllureGhObject {


    @Step("Открываем главную страницу")
    public AllureGhObject openMainPage() {
        open("https://github.com/");
        return this;
    }

    @Step("Поиск репо {repo}")
    public AllureGhObject searchRepo(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
        return this;
    }

    @Step("Переход по ссылке на репозиторий {repo}")
    public AllureGhObject clickRepo(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("Открываем Issues")
    public AllureGhObject clickIssue() {
        $(partialLinkText("Issues")).click();
        return this;
    }

    @Attachment(value = "Cкриншот", type = "image/png", fileExtension = "png")
    public byte[] attachSreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Проверяем что существует Issue с номером {number}")
    public AllureGhObject shouldIssueNumber(int number) {
        $(withText("#" + number)).should(Condition.visible);
        attachSreenshot();
        return this;
    }
}
