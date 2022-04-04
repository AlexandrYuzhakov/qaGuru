import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestStudentRegistration {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        String name = "Alex";
        String lastName = "Egorov";
        String email = "alex@egorov.com";
        String mobile = "1234567899";
        String yearOfBirth = "1980";
        String monthOfBirth = "November";
        String dataOfBirth = "17";
        String subjects = "English";
        String currentAddress = "Some address 11";
        String state = "Uttar Pradesh";
        String city = "Lucknow";


        open("/automation-practice-form");
        getWebDriver().manage().window().maximize();

        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x("//*[@for='gender-radio-1']").click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionContainingText(yearOfBirth);
        $(".react-datepicker__month-select").selectOptionContainingText(monthOfBirth);
        $x("//*[contains(@aria-label, '" + monthOfBirth + " " + dataOfBirth + "th, " + yearOfBirth + "')]").click();
        $x("//div[@class='subjects-auto-complete__control css-yk16xz-control']").click();
        $("#subjectsInput").shouldBe(visible).setValue(subjects).pressEnter();
        $x("//*[@for='hobbies-checkbox-1']").click();

        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting + "\\src\\test\\resources\\Image11.jpg");
        $("#uploadPicture").uploadFile(file);


        $("#currentAddress").setValue(currentAddress);
        $x("//*[@id='state']//*[@class=' css-tlfecz-indicatorContainer']").click();
        $("#react-select-3-input").shouldBe(visible).setValue(state).pressEnter();
        $x("//*[@id='city']//*[@class=' css-tlfecz-indicatorContainer']").click();
        $("#react-select-4-input").shouldBe(visible).setValue(city).pressEnter();
        $("#submit").click();

        Configuration.timeout = 20000;
        $(".table-responsive").shouldBe(visible).shouldHave(text(name), text(lastName),
                text(email), text(mobile), text(subjects), text(currentAddress), text(city), text(state), text("image11.jpg"),
                text("Sports"), text("17 November,1980"), text("Male"));
    }
}
