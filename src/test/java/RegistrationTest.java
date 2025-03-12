import base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import pages.Registration;
import io.github.bonigarcia.wdm.WebDriverManager;
import static base.BasePage.getTokenUser;
import static base.Const.Urls.BASE_URL;
import static com.codeborne.selenide.Selenide.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class RegistrationTest extends BaseTest {
    public Registration registration;

    @Test
    @DisplayName("Успешная регистрация, UI регистрация")
    public void successfulRegistrationTest() {
        registration = open(BASE_URL, Registration.class)
                .btnPersonalAreaClick()
                .linkRegClick()
                .registration(user.getName(), user.getEmail(), user.getPassword())
                .authorizationAndAssertNameUser(user);

        accessToken = getTokenUser();
    }

    @Test
    @DisplayName("Успешная регистрация, API регистрация")
    public void successfulRegistrationAPITest() {
        ValidatableResponse createUser = basePage.createUser(user);
        int statusCode = createUser.extract().statusCode();
        Boolean success = createUser.extract().path("success");
        accessToken = createUser.extract().path("accessToken");

        Assert.assertTrue(success);
        Assert.assertEquals(HTTP_OK, statusCode);
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    public void errorForInvalidPasswordTest() {
        registration = open(BASE_URL, Registration.class)
                .btnPersonalAreaClick()
                .linkRegClick()
                .registration(user.getName(), user.getEmail(), faker.number().digits(4))
                .assertErrorSetInputPassword();

        accessToken = null;
    }

}