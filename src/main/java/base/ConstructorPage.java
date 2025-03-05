package base;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$x;

public class ConstructorPage {

    private static final String INGREDIENT_TEXT = "//p[@class='BurgerIngredient_ingredient__text__yp3dH']";
    private final ElementsCollection ingredientElements = $$x(INGREDIENT_TEXT);


    public String getIngredientText(int index) {
        return ingredientElements.get(index).hover().text();
    }
}