import base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import model.UserCred;
import org.junit.Test;
import pages.Entrance;
import io.github.bonigarcia.wdm.WebDriverManager;
import static base.BasePage.getTokenUser;
import static base.Const.Urls.*;
import static com.codeborne.selenide.Selenide.open;

public class EntranceTest extends BaseTest {
    public Entrance entrance;
    private UserCred userCred = new UserCred();

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginByClickingTheLoginButtonOnTheMainPageTest() {
        entrance = open(BASE_URL, Entrance.class).registration(userCred.from()).authorizationAndCheckName(userCred.from());
        accessToken = getTokenUser();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginThroughTheButtonPersonalAccountTest() {
        entrance = open(BASE_URL, Entrance.class).registration(userCred.from()).authorizationTheButtonPersonalAccount(userCred.from());
        accessToken = getTokenUser();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginThroughTheButtonInTheRegistrationFormTest() {
        entrance = open(BASE_URL, Entrance.class).registration(userCred.from()).authorizationThePageRegister(userCred.from());
        accessToken = getTokenUser();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaTheButtonInThePasswordRecoveryFormTest() {
        entrance = open(BASE_URL, Entrance.class).registration(userCred.from()).authorizationThePageForgotPassword(userCred.from());
        accessToken = getTokenUser();
    }
}