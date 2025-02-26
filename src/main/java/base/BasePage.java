package base;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;

import static base.Const.Urls.*;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.restassured.RestAssured.given;

public class BasePage {
    public static void openSite(String url) {
        Selenide.open(url);
    }

    public static String getTokenUser() {
        return localStorage().getItem("accessToken");
    }

    public static String getUrlCurrentPage() {
        return url();
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(CREATE_USER_URI_API)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse authorization(User user, String token) {
        return given()
                .header("Content-type", "Application/json")
                .and()
                .header("Authorization", token)
                .and()
                .body(user)
                .when()
                .post(AUTHORIZATION_USER_URI_API)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Content-type", "Application/json")
                .and()
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER_URI_API)
                .then();
    }
}