import Pages.RegistrationPage;
import Pages.ResultPage;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static java.lang.String.format;

@DisplayName("Параметрайз клас")
public class ParametrizeTest {

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

    @ValueSource(strings = {
            "Ivan",
            "Boris",
    })
    @ParameterizedTest(name = "Параметрайз тест с одним аргументом имени {0}")
    void studentRegistrationTest(String testData) {

        String name = testData,
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

    @CsvSource(value = {
            "Ivan | Ivanov",
            "Boris | Borisov"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Параметрайз тест с двумя аргументам имени {0}, фамилии {1}")
    void studentRegistrationComplexTest(String nameData, String lastNameData) {

        String name = nameData,
                lastName = lastNameData,
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


    static Stream<Arguments> metodStudentRegistrationComplexTest() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov"),
                Arguments.of("Boris", "Borisov")
        );
    }

    @MethodSource("metodStudentRegistrationComplexTest")
    @ParameterizedTest(name = "Параметрайз тест с двумя аргументам имени {0}, фамилии {1}")
    void metodStudentRegistrationComplexTest(String nameData, String lastNameData) {
        String name = nameData,
                lastName = lastNameData,
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
