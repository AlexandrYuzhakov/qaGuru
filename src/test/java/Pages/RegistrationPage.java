package Pages;

import Pages.Component.CalendarComponent;
import Pages.Component.StateandCityComponent;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class RegistrationPage {

    CalendarComponent calendar = new CalendarComponent();
    StateandCityComponent stateAndCity = new StateandCityComponent();


    SelenideElement inputFirstName = $("#firstName");
    SelenideElement inputLastName = $("#lastName");
    SelenideElement inputEmail = $("#userEmail");
    SelenideElement inputGender = $("#genterWrapper");
    SelenideElement inputPhoneNumber = $("#userNumber");
    SelenideElement dateOfBirth = $("#dateOfBirthInput");
    SelenideElement inputSubject = $("#subjectsInput");
    SelenideElement inputHobby = $("#hobbiesWrapper");
    SelenideElement pictureUpload = $("#uploadPicture");
    SelenideElement inputAddress = $("#currentAddress");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        getWebDriver().manage().window().maximize();
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        inputFirstName.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        inputLastName.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String userEmail) {
        inputEmail.setValue(userEmail);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        inputGender.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String PhoneNumber) {
        inputPhoneNumber.setValue(PhoneNumber);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirth.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        inputSubject.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        inputHobby.$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String image) {
        pictureUpload.uploadFromClasspath(image);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        inputAddress.setValue(address);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        $("#state").click();
        stateAndCity.setState(state);
        $("#city").click();
        stateAndCity.setCity(city);
        return this;
    }

    public RegistrationPage submitForm() {
        $("#submit").click();
        return this;
    }
}
