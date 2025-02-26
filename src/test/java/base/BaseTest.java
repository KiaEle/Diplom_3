package base;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import static base.Const.Urls.BASE_URL;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    public String accessToken;
    public BasePage basePage;

    public Faker faker;
    public User user;
    public WebDriver driver;


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        basePage = new BasePage();
        faker = new Faker();
        user = new User(
                faker.name().username(),
                faker.name().firstName() + faker.number().digits(4) + "@yandex.ru",
                faker.number().digits(10));

        WebDriverManager.chromedriver().setup(); // Автоматическая настройка chromedriver
        driver = new ChromeDriver(); // Создание экземпляра ChromeDriver
        driver.get(BASE_URL); // Открытие нужной страницы
    }

    @After
    public void clear() {
        if (accessToken != null) {
            ValidatableResponse delete = basePage.deleteUser(accessToken);
            delete.statusCode(202);
            boolean success = delete.extract().path("success");
            Assert.assertTrue(success);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}