import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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
    void studentRegistrationTest() {
        String name = "Alex";
        String lastName = "Egorov";
        String email = "alex@egorov.com";
        String gender = "Male";
        String mobile = "1234567899";
        String yearOfBirth = "1980";
        String monthOfBirth = "November";
        String dataOfBirth = "17";
        String subjects = "English";
        String hobby = "Sports";
        String file = "Image11.jpg";
        String currentAddress = "Some address 11";
        SelenideElement stateCityWrapper = $("#stateCity-wrapper");
        String state = "Uttar Pradesh";
        String city = "Lucknow";


        open("/automation-practice-form");
        getWebDriver().manage().window().maximize();

        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionContainingText(yearOfBirth);
        $(".react-datepicker__month-select").selectOptionContainingText(monthOfBirth);
        $x("//*[contains(@aria-label, '" + monthOfBirth + " " + dataOfBirth + "th, " + yearOfBirth + "')]").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(file);
        $("#currentAddress").setValue(currentAddress);
        stateCityWrapper.$(byText("Select State")).click();
        stateCityWrapper.$(byText(state)).click();
        stateCityWrapper.$(byText("Select City")).click();
        stateCityWrapper.$(byText(city)).click();
        $("#submit").click();

        Configuration.timeout = 20000;
        $(".table-responsive").shouldHave(
                text(name),
                text(lastName),
                text(email),
                text(mobile),
                text(subjects),
                text(currentAddress),
                text(city), text(state),
                text(file),
                text(hobby),
                text("" + dataOfBirth + " " + monthOfBirth + "," + yearOfBirth + ""),
                text(gender));
    }
}
