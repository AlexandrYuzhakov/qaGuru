import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@DisplayName("Тест Лямбда шаги через step")
public class TestGhAllureLambda {


    private static final String REPOSITORY = "AlexandrYuzhakov/qaGuru";
    private static final int ISSUE_NUMBER = 3;

    @Test
    @DisplayName("Проверка issue с номером {ISSUE_NUMBER} в репо {REPOSITORY}")
    public void testGhIssue() {
        SelenideLogger.addListener("allure0", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим на  репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Кликаем  Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверяем Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText("#1")).should(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}