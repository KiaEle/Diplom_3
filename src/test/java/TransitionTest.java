import base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.Transition;
import io.github.bonigarcia.wdm.WebDriverManager;
import static base.BasePage.getTokenUser;
import static base.Const.Urls.BASE_URL;
import static com.codeborne.selenide.Selenide.*;

public class TransitionTest extends BaseTest {
    public Transition transition;

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccountTest() {
        transition = open(BASE_URL, Transition.class)
                .btnPersonalAreaClick()
                .linkRegistrationClick()
                .registration(user.getName(), user.getEmail(), user.getPassword())
                .btnPersonalAreaClick()
                .authorizationAndAssertValueInputNameThePersonalArea(user);

        accessToken = getTokenUser();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transitionFromPersonalAccountToTheDesignerTest() {
        transition = open(BASE_URL, Transition.class)
                .btnPersonalAreaClick()
                .linkRegistrationClick()
                .registration(user.getName(), user.getEmail(), user.getPassword())
                .btnPersonalAreaClick()
                .authorizationAndAssertValueInputNameThePersonalArea(user)
                .btnDesignerClick()
                .assertTitleTheMainPage();

        accessToken = getTokenUser();
    }

    @Test
    @DisplayName("Переход из личного на логотип Stellar Burgers")
    public void movingFromPersonalToStellarBurgersLogoTest() {
        transition = open(BASE_URL, Transition.class)
                .btnPersonalAreaClick()
                .linkRegistrationClick()
                .registration(user.getName(), user.getEmail(), user.getPassword())
                .btnPersonalAreaClick()
                .authorizationAndAssertValueInputNameThePersonalArea(user)
                .btnLogoClick()
                .assertTitleTheMainPage();

        accessToken = getTokenUser();
    }

    @Test
    @DisplayName("Проверь выход по кнопке «Выйти» в личном кабинете")
    public void checkThExitByClickingTheExitButtonInYourAccountTest() {
        transition = open(BASE_URL, Transition.class)
                .btnPersonalAreaClick()
                .linkRegistrationClick()
                .registration(user.getName(), user.getEmail(), user.getPassword())
                .btnPersonalAreaClick()
                .authorizationAndAssertValueInputNameThePersonalArea(user)
                .btnLExitClick()
                .assertUrlLogin();

        accessToken = getTokenUser();
    }

}