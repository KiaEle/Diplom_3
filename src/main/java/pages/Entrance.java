package pages;

import base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import model.User;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static base.Const.Urls.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Entrance extends BasePage {
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Войти в аккаунт')]")
    private SelenideElement mainPageBtnAuth;

    @FindBy(how = How.XPATH, using = "//label[@class='input__placeholder text noselect text_type_main-default' and contains(text(), 'Имя')]/../input")
    private SelenideElement inputName;
    @FindBy(how = How.XPATH, using = "//label[@class='input__placeholder text noselect text_type_main-default' and contains(text(), 'Email')]/../input")
    private SelenideElement inputEmail;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Войти')]")
    private SelenideElement btnAuth;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement btnAuth2;

    @FindBy(how = How.XPATH, using = "//p[@class='AppHeader_header__linkText__3q_va ml-2' and contains(text(), 'Личный Кабинет')]")
    private SelenideElement btnPersonalArea;

    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Зарегистрироваться')]")
    private SelenideElement btnReg;

    @FindBy(how = How.NAME, using = "Name")
    private SelenideElement fieldNameInProfile;

    @Step("Клик на кнопку авторизации на главной странице")
    public Entrance mainPageBtnAuthClick() {
        mainPageBtnAuth.shouldBe(Condition.visible).click();
        return Selenide.page(Entrance.class);
    }

    @Step("Ввести имя пользователя")
    public Entrance setInputName(String name) {
        inputName.shouldBe(Condition.enabled).setValue(name);
        return Selenide.page(Entrance.class);
    }

    @Step("Ввести е-мейл пользователя")
    public Entrance setInputEmail(String email) {
        inputEmail.shouldBe(Condition.enabled).setValue(email);
        return Selenide.page(Entrance.class);
    }

    @Step("Ввести пароль пользователя")
    public Entrance setInputPassword(String password) {
        inputPassword.shouldBe(Condition.enabled).setValue(password);
        return Selenide.page(Entrance.class);
    }

    public String getInputValue() {
        return fieldNameInProfile.should(exist).getValue();
    }



    @Step("Регистрация пользователя")
    public Entrance registration(User user) {
        btnPersonalArea.shouldBe(Condition.visible).click();
        btnReg.shouldBe(Condition.visible).click();
        setInputName(user.getName());
        setInputEmail(user.getEmail());
        setInputPassword(user.getPassword());
        btnReg.shouldBe(Condition.enabled).click();
        return Selenide.page(Entrance.class);
    }

    @Step("Авторизация пользователя")
    public void authorization(User user) {
        setInputEmail(user.getEmail());
        setInputPassword(user.getPassword());
        btnAuth.shouldBe(Condition.visible).click();
        sleep(1000);
    }

    @Step("Авторизация пользователя и проверка имени в личном кабинете")
    public Entrance authorizationAndCheckName(User user) {
        open(BASE_URL);
        mainPageBtnAuthClick();
        authorization(user);
        btnPersonalArea.shouldBe(Condition.visible).click();
        Assert.assertEquals(user.getName(), getInputValue());
        return Selenide.page(Entrance.class);
    }

    @Step("Авторизация пользователя через кнопку личного кабинета")
    public Entrance authorizationTheButtonPersonalAccount(User user) {
        btnPersonalArea.shouldBe(Condition.visible).click();
        authorization(user);
        btnPersonalArea.shouldBe(Condition.visible).click();
        Assert.assertEquals(user.getName(), getInputValue());
        return Selenide.page(Entrance.class);
    }

    @Step("Авторизация пользователя из страницы регистрации")
    public Entrance authorizationThePageRegister(User user) {
        btnAuth.shouldBe(Condition.visible).click();
        authorization(user);
        btnPersonalArea.shouldBe(Condition.visible).click();
        Assert.assertEquals(user.getName(), getInputValue());
        return Selenide.page(Entrance.class);
    }

    @Step("Авторизация пользователя из страницы восстановления пароля")
    public Entrance authorizationThePageForgotPassword(User user) {
        open(BASE_URL + FORGOT_PASSWORD_URL);
        btnAuth.shouldBe(Condition.visible).click();
        authorization(user);
        btnPersonalArea.shouldBe(Condition.visible).click();
        Assert.assertEquals(user.getName(), getInputValue());
        return Selenide.page(Entrance.class);
    }
}