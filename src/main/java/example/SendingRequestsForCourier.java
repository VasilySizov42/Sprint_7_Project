package example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static example.BaseURLHandlesAndWarnings.*;
import static java.lang.String.valueOf;
public class SendingRequestsForCourier {
    @Step("creating a new courier")
    public static ValidatableResponse createCourier(Courier courier) {
        return OtherRequests.scope()
                .body(courier)
                .when()
                .post(BASIC_HANDLE + COURIER_HANDLE)
                .then().log().body()
                ;
    }

    @Step("login a courier")
    public static ValidatableResponse loggedInCourier(Credentials credentials) {
        return OtherRequests.scope()
                .body(credentials)
                .when()
                .post(BASIC_HANDLE + COURIER_HANDLE + LOGIN_HANDLE)
                .then().log().body()
                ;
    }

    @Step("delete a courier")
    public static ValidatableResponse deleteCourier(Courier courier) {
        int id = getCourierId(Credentials.from(courier));

        return OtherRequests.scope()
                .body(Map.of("id", valueOf(id)))
                .when()
                .delete(BASIC_HANDLE + COURIER_HANDLE + "/" + id)
                .then().log().body()
                ;
    }

    @Step("courier data generation")
    public static Courier genericCourier() {
        return new Courier("Bazz"+ RandomStringUtils.randomAlphanumeric(3,5),
                RandomStringUtils.randomAlphanumeric(7,10), "Bazz");
    }

    public static int getCourierId(Credentials credentials) {
        return loggedInCourier(credentials)
                .extract()
                .path("id")
                ;
    }
}
