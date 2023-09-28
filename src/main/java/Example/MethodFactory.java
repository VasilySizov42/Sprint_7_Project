package Example;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static Example.DataForTesting.BASIC_HANDLE;
import static Example.DataForTesting.YANDEX_SCOOTER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class MethodFactory {

    @Step("checking the orders.id not null")
    public static void checkOrdersIdNotNull(Response response) {
        response.then().assertThat().body("orders[0].id", notNullValue());
        //response.then().assertThat().body("orders[1].id", equalTo(37));
        System.out.println(response.body().asString());
    }

    @Step ("checking the Status Code")
    public static void checkForStatusCode(Response response, int status) {
        response.then().statusCode(status);
        System.out.println(response.statusCode());
    }

    @Step ("getting Response via GET Request")
    public static Response getResponseViaGet(String finalHandle) {
        Response response = given().log().all()
                .when().get(BASIC_HANDLE + finalHandle);
        return response;
    }
    public static Courier genericCourier() {
        return new Courier("Bazz"+ RandomStringUtils.randomAlphanumeric(3,5),
                RandomStringUtils.randomAlphanumeric(7,10), "Bazz");
    }
    public static Response loginCourier(Credentials creds) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(YANDEX_SCOOTER)
                .body(creds)
                .when()
                .post(BASIC_HANDLE + "courier/login");
    }
    public static Response createCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(YANDEX_SCOOTER)
                .body(courier)
                .when()
                .post(BASIC_HANDLE + "courier");
    }
}
