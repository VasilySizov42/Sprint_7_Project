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
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

public class MethodFactory {
    public static RequestSpecification scope() {
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(YANDEX_SCOOTER)
                ;
    }
    @Step ("getting Response via GET Request")
    public static ValidatableResponse getResponseViaGet(String finalHandle) {
        return scope()
                .get(BASIC_HANDLE + finalHandle)
                .then().log().all()
                ;
    }
    @Step ("checking the Status Code")
    public static void checkForStatusCode(ValidatableResponse response, int status) {
        response.statusCode(status)
        ;
    }
    @Step("checking the orders.id not null")
    public static void checkOrdersIdNotNull(ValidatableResponse response) {
        response.assertThat()
                .body("orders[0].id", notNullValue())
        ;
    }
    @Step("courier data generation")
    public static Courier genericCourier() {
        return new Courier("Bazz"+ RandomStringUtils.randomAlphanumeric(3,5),
                RandomStringUtils.randomAlphanumeric(7,10), "Bazz");
    }
    public static ValidatableResponse loginCourier(Credentials credentials) {
        return scope()
                .body(credentials)
                .when()
                .post(BASIC_HANDLE + COURIER_HANDLE + LOGIN_HANDLE)
                .then().log().all()
                ;
    }
    public static ValidatableResponse createCourier(Courier courier) {
        return scope()
                .body(courier)
                .when()
                .post(BASIC_HANDLE + COURIER_HANDLE)
                .then().log().all()
                ;
    }
    public static void deleteCourier(Courier courier) {
        int id = getCourierId(Credentials.from(courier));
        System.out.println(id);
        scope()
                .body(Map.of("id", valueOf(id)))
                .when()
                .delete(BASIC_HANDLE + COURIER_HANDLE + "/" + id)
                .then().log().all()
                .statusCode(HTTP_OK)
                .body("ok", is(true))
                ;
    }
    public static void assertCreatedSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_CREATED)
                .body("ok", is(true))
                ;
    }
    public static void loggedInSuccessfully(Credentials credentials) {
        int id = loginCourier(credentials)
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("id")
                ;
        assert id!=0;
    }
    public static int getCourierId(Credentials credentials) {
        return loginCourier(credentials)
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("id")
                ;
    }
}
