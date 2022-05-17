import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;


@DisplayName("Тест с аннотацией @Step")
public class TestGhAllureStep {

    private static final String REPOSITORY = "AlexandrYuzhakov/qaGuru";
    private static final int ISSUE_NUMBER = 3;

    @Test
    @DisplayName("Поиск репозитория")
    void testGitHub() {
        SelenideLogger.addListener("allure1", new AllureSelenide());
        page(new AllureGhObject()).openMainPage()
                .searchRepo(REPOSITORY)
                .clickRepo(REPOSITORY)
                .clickIssue()
                .shouldIssueNumber(ISSUE_NUMBER);

    }
}