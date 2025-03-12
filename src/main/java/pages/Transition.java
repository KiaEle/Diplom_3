package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import model.User;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static base.Const.Urls.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class Transition {
    @FindBy(how = How.XPATH, using = "//p[@class='AppHeader_header__linkText__3q_va ml-2' and contains(text(), 'Личный Кабинет')]")
    private SelenideElement btnPersonalArea;
    @FindBy(how = How.XPATH, using = "//label[@class='input__placeholder text noselect text_type_main-default' and contains(text(), 'Имя')]/../input")
    private SelenideElement inputName;
    @FindBy(how = How.XPATH, using = "//label[@class='input__placeholder text noselect text_type_main-default' and contains(text(), 'Email')]/../input")
    private SelenideElement inputEmail;
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement inputPassword;
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Войти')]")
    private SelenideElement btnAuth;
    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Зарегистрироваться')]")
    private SelenideElement btnReg;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Выход')]")
    private SelenideElement btnExit;
    @FindBy(how = How.NAME, using = "Name")
    private SelenideElement fieldNameInProfile;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Конструктор')]")
    private SelenideElement btnDesigner;

    @FindBy(how = How.XPATH, using = "//*[@id='root']/div/header/nav/div/a")
    private SelenideElement logo;

    @Step("Ввод имени")
    public Registration setInputName(String name) {
        inputName.shouldBe(Condition.enabled).setValue(name);
        return Selenide.page(Registration.class);
    }

    @Step("Ввод е-мейла")
    public Transition setInputEmail(String email) {
        inputEmail.shouldBe(Condition.enabled).setValue(email);
        return Selenide.page(Transition.class);
    }
    @Step("Ввод пароля")
    public Transition setInputPassword(String password) {
        inputPassword.shouldBe(Condition.enabled).setValue(password);
        return Selenide.page(Transition.class);
    }

    public String getInputValue() {
        return fieldNameInProfile.should(exist).getValue();
    }

    @Step("Нажатие на кнопку личный кабинет")
    public Transition btnPersonalAreaClick() {
        btnPersonalArea.shouldBe(Condition.enabled).click();
        return Selenide.page(Transition.class);
    }

    @Step("Нажатие на кнопку изменить данные")
    public Transition btnDesignerClick() {
        btnDesigner.shouldBe(Condition.enabled).click();
        return Selenide.page(Transition.class);
    }

    @Step("Нажатие на кнопку логотипа")
    public Transition btnLogoClick() {
        logo.shouldBe(Condition.enabled).click();
        return Selenide.page(Transition.class);
    }

    @Step("Нажатие на кнопку выхода")
    public Transition btnLExitClick() {
        btnExit.shouldBe(Condition.enabled).click();
        return Selenide.page(Transition.class);
    }

    @Step("Нажатие на кнопку регистрации")
    public Transition linkRegistrationClick() {
        btnReg.shouldBe(Condition.enabled).click();
        return Selenide.page(Transition.class);
    }

    @Step("Авторизация и проверка значения в поле имени личного кабинета пользователя")
    public Transition authorizationAndAssertValueInputNameThePersonalArea(User user) {
        open(BASE_URL + AUTH_URL);
        setInputEmail(user.getEmail());
        setInputPassword(user.getPassword());
        btnAuth.shouldBe(Condition.enabled).click();
        btnPersonalArea.shouldBe(visible).click();
        fieldNameInProfile.shouldBe(visible);
        Assert.assertEquals(user.getName(), getInputValue());
        return Selenide.page(Transition.class);
    }

    @Step("Регистрация пользователя")
    public Transition registration(String name, String email, String password) {
        setInputName(name);
        setInputEmail(email);
        setInputPassword(password);
        btnReg.shouldBe(Condition.enabled).click();
        return Selenide.page(Transition.class);
    }

    @Step("Проверка заголовка на главной странице")
    public Transition assertTitleTheMainPage() {
        $("h1").shouldBe(visible).shouldHave(text("Соберите бургер"));
        return Selenide.page(Transition.class);
    }

    @Step("Проверка урла после авторизации")
    public Transition assertUrlLogin() {
        webdriver().shouldHave(url(BASE_URLLOG));
        return Selenide.page(Transition.class);
    }

    @Step("Неявное ожидание")
    public Transition sleep(int sec) {
        Selenide.sleep(sec * 1000);
        return Selenide.page(Transition.class);
    }

}