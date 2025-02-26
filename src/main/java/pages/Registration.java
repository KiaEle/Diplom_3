package pages;

import base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import model.User;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static base.Const.Urls.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class Registration extends BasePage {
    @FindBy(how = How.XPATH, using = "//p[@class='AppHeader_header__linkText__3q_va ml-2' and contains(text(), 'Личный Кабинет')]")
    private SelenideElement btnPersonalArea;
    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement linkReg;

    @FindBy(how = How.XPATH, using = "//label[@class='input__placeholder text noselect text_type_main-default' and contains(text(), 'Имя')]/../input")
    private SelenideElement inputName;

    @FindBy(how = How.XPATH, using = "//label[@class='input__placeholder text noselect text_type_main-default' and contains(text(), 'Email')]/../input")
    private SelenideElement inputEmail;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Зарегистрироваться')]")
    private SelenideElement btnReg;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Войти')]")
    private SelenideElement btnAuth;
    @FindBy(how = How.NAME, using = "Name")
    private SelenideElement fieldNameInProfile;

    @Step("Клик на кнопку личного кабинета")
    public Registration btnPersonalAreaClick() {
        btnPersonalArea.shouldBe(Condition.enabled).click();
        return Selenide.page(Registration.class);
    }

    @Step("Клик на ссылку регистрации")
    public Registration linkRegClick() {
        linkReg.shouldBe(Condition.enabled).click();
        return Selenide.page(Registration.class);
    }

    @Step("Ввести имя")
    public Registration setInputName(String name) {
        inputName.shouldBe(Condition.enabled).setValue(name);
        return Selenide.page(Registration.class);
    }

    @Step("Ввести е-мейл")
    public Registration setInputEmail(String email) {
        inputEmail.shouldBe(Condition.enabled).setValue(email);
        return Selenide.page(Registration.class);
    }

    @Step("Ввести пароль")
    public Registration setInputPassword(String password) {
        inputPassword.shouldBe(Condition.enabled).setValue(password);
        return Selenide.page(Registration.class);
    }

    public String getInputValue() {
        return fieldNameInProfile.should(exist).getValue();
    }

    @Step("Регистрация пользователя")
    public Registration registration(String name, String email, String password) {
        setInputName(name);
        setInputEmail(email);
        setInputPassword(password);
        btnReg.shouldBe(Condition.enabled).click();
        return Selenide.page(Registration.class);
    }

    @Step("Авторизация пользователя и проверка имени пользователя в личном кабинете")
    public Registration authorizationAndAssertNameUser(User user) {
        open(BASE_URL + AUTH_URL);
        setInputEmail(user.getEmail());
        setInputPassword(user.getPassword());
        btnAuth.shouldBe(Condition.enabled).click();
        sleep(2);
        btnPersonalArea.shouldBe(Condition.visible).click();
        Assert.assertEquals(user.getName(), getInputValue());
        return Selenide.page(Registration.class);
    }

    @Step("Неявное ожидание")
    public Registration sleep(int sec) {
        Selenide.sleep(sec * 1000);
        return Selenide.page(Registration.class);
    }

    @Step("Проверка ошибки в поле ввода пароля")
    public Registration assertErrorSetInputPassword() {
        $x("//p[@class='input__error text_type_main-default']").shouldBe(Condition.visible).shouldHave(Condition.text("Некорректный пароль"));
        return Selenide.page(Registration.class);
    }
}