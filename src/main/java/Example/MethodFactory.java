package Example;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static Example.DataForTesting.*;
import static io.restassured.RestAssured.given;
import static java.lang.String.valueOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

public class MethodFactory {
    public static RequestSpecification scope() {
        return  given().log().method()
                .contentType(ContentType.JSON)
                .baseUri(YANDEX_SCOOTER)
                ;
    }
    @Step ("getting Response via GET Request")
    public static ValidatableResponse getResponseViaGet(String finalHandle) {
        return scope()
                .get(BASIC_HANDLE + finalHandle)
                .then().log().body()
                ;
    }
    @Step("courier data generation")
    public static Courier genericCourier() {
        return new Courier("Bazz"+ RandomStringUtils.randomAlphanumeric(3,5),
                RandomStringUtils.randomAlphanumeric(7,10), "Bazz");
    }
    @Step("creating a new courier")
    public static ValidatableResponse createCourier(Courier courier) {
        return scope()
                .body(courier)
                .when()
                .post(BASIC_HANDLE + COURIER_HANDLE)
                .then().log().body()
                ;
    }
    @Step("login a courier")
    public static ValidatableResponse loggedInCourier(Credentials credentials) {
        return scope()
                .body(credentials)
                .when()
                .post(BASIC_HANDLE + COURIER_HANDLE + LOGIN_HANDLE)
                .then().log().body()
                ;
    }
    @Step("delete a courier")
    public static ValidatableResponse deleteCourier(Courier courier) {
        int id = getCourierId(Credentials.from(courier));

        return scope()
                .body(Map.of("id", valueOf(id)))
                .when()
                .delete(BASIC_HANDLE + COURIER_HANDLE + "/" + id)
                .then().log().body()
                ;
    }
    @Step("create order")
    public static ValidatableResponse createOrder(Order order) {
        return scope()
                .body(order)
                .when()
                .post(BASIC_HANDLE + ORDERS_HANDLE)
                .then().log().body()
                ;
    }

    @Step ("checking the Status Code")
    public static void checkForStatusCode(ValidatableResponse response, int status) {
        response.assertThat()
                .statusCode(status)
        ;
    }
    @Step ("checking the parameter in the response has some value")
    public static void checkParamWithValue(ValidatableResponse response, String param, Object value) {
        response.assertThat()
                .body(param, is(value))
        ;
    }
    @Step ("checking the parameter in the response is not null")
    public static void checkParamIsNotNull(ValidatableResponse response, String param) {
        response.assertThat()
                .body(param, notNullValue())
        ;
    }
    @Step ("checking the \"Ok\" in the response is true")
    public static void checkCreatedWithOkTrue(ValidatableResponse response) {
        response.assertThat()
                .body("ok", is(true))
        ;
    }
    @Step ("checking the not null \"track\" in the response is not null")
    public static void checkCreatedWithTrackNotNull(ValidatableResponse response) {
        response.assertThat()
                .body("track", notNullValue())
        ;
    }
    @Step("checking the not null \"orders.id\" in the response is not null")
    public static void checkOrdersIdNotNull(ValidatableResponse response) {
        response.assertThat()
                .body("orders[0].id", notNullValue())
        ;
    }
    @Step("checking the \"id\" in the response is not null")
    public static void checkLoggedInWithNotNullId(ValidatableResponse response) {
        response.assertThat()
                .body("id", notNullValue());
    }
    public static int getCourierId(Credentials credentials) {
        return loggedInCourier(credentials)
                .extract()
                .path("id")
                ;
    }

}
