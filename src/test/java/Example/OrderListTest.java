package Example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static Example.DataForTesting.ORDERS_HANDLE;
import static Example.DataForTesting.YANDEX_SCOOTER;
import static Example.MethodFactory.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class OrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = YANDEX_SCOOTER;
    }
    @Test
    @DisplayName("Check order list without CourierId of /api/v1/orders")
    @Description("Basic test for /api/v1/orders")
    public void checkOrderListWithCourierIdNull() {

        var response = getResponseViaGet(ORDERS_HANDLE);

        checkForStatusCode(response, HTTP_OK);
        checkOrdersIdNotNull(response);
    }
}
