package example.methods;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.notNullValue;

public class CheckingResponseForOrder {
    @Step("checking the \"track\" in the response is not null")
    public static void checkCreatedWithTrackNotNull(ValidatableResponse response) {
        response.assertThat()
                .body("track", notNullValue())
        ;
    }

    @Step("checking the \"orders.id\" in the response is not null")
    public static void checkOrdersIdNotNull(ValidatableResponse response) {
        response.assertThat()
                .body("orders[0].id", notNullValue())
        ;
    }
}
