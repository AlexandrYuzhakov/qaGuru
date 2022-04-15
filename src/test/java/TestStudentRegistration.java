import Pages.RegistrationPage;
import Pages.ResultPage;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;


public class TestStudentRegistration {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();
    ResultPage resultPage = new ResultPage();


    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 100000;
    }


    @Test
    void studentRegistrationTest() {

        String name = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                gender = "Male",
                mobile = faker.phoneNumber().subscriberNumber(10),
                yearOfBirth = "1980",
                monthOfBirth = "November",
                dataOfBirth = "17",
                subjects = "English",
                hobby = "Sports",
                file = "Image11.jpg",
                currentAddress = faker.address().fullAddress(),
                state = "Uttar Pradesh",
                city = "Lucknow",

                expFullName = format("%s %s", name, lastName),
                expDate = format("%s %s,%s", dataOfBirth, monthOfBirth, yearOfBirth),
                expLocation = format("%s %s", state, city);

        registrationPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(mobile)
                .setBirthDate(dataOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subjects)
                .setHobby(hobby)
                .uploadPicture(file)
                .setAddress(currentAddress)
                .setStateAndCity(state, city)
                .submitForm();

        resultPage.checkTitle("Thanks for submitting the form")
                .checkResult("Student Name", expFullName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", expDate)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", file)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", expLocation);
    }
}
