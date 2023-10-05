package example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static example.BaseURLHandlesAndWarnings.BASIC_HANDLE;
import static example.BaseURLHandlesAndWarnings.ORDERS_HANDLE;

public class SendingRequestsForOrder {
    @Step("create order")
    public static ValidatableResponse createOrder(Order order) {
        return OtherRequests.scope()
                .body(order)
                .when()
                .post(BASIC_HANDLE + ORDERS_HANDLE)
                .then().log().body()
                ;
    }
}
