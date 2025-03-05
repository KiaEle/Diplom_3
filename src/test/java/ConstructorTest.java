import base.ConstructorPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import static base.Const.Urls.BASE_URL;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@RunWith(Parameterized.class)
public class ConstructorTest {

    public int index;
    public String expectedName;
    public ConstructorTest(int index, String expectedName) {
        this.index = index;
        this.expectedName = expectedName;
    }

  

    @Parameterized.Parameters(name = "Список ингредиентов. Тестовые данные: {0} {1} {2} {3} {4} {5} {6} {7} {8} {9} {10} {11} {12} {13} {14} {15}")
    public static Object[][] getText() {
        return new Object[][] {
                {0, "Флюоресцентная булка R2-D3"},
                {1, "Краторная булка N-200i"},
                {2, "Соус Spicy-X"},
                {3, "Соус фирменный Space Sauce"},
                {4,"Соус традиционный галактический"},
                {5, "Соус с шипами Антарианского плоскоходца"},
                {6, "Мясо бессмертных моллюсков Protostomia"},
                {7, "Говяжий метеорит (отбивная)"},
                {8, "Биокотлета из марсианской Магнолии"},
                {9, "Филе Люминесцентного тетраодонтимформа"},
                {10,"Хрустящие минеральные кольца"},
                {11, "Плоды Фалленианского дерева"},
                {12, "Кристаллы марсианских альфа-сахаридов"},
                {13, "Мини-салат Экзо-Плантаго"},
                {14, "Сыр с астероидной плесенью"},
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Автоматическая настройка chromedriver
        WebDriver driver = new ChromeDriver(); // Создание экземпляра ChromeDriver
        driver.get(BASE_URL); // Открытие нужной страницы
    }


    @Test
    @DisplayName("Раздел «Конструктор». Проверь, что работают переходы к разделам: «Булки», «Соусы»,«Начинки».")
    public void checkThatSectionTransitionsWorkTest() {
        open(BASE_URL);
        // Используем класс ConstructorPage для работы с элементами
        ConstructorPage constructorPage = new ConstructorPage();
        String actualText = constructorPage.getIngredientText(index);

        // Проверяем, что текст элемента соответствует ожидаемому
        Assert.assertEquals(expectedName, actualText);
    }

    @After
    public void clear() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}