package Example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static Example.DataForTesting.ORDERS_HANDLE;
import static Example.MethodFactory.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class OrderListTest {

    @Test
    @DisplayName("Check order list")
    @Description("Test for getting a general list of orders without CourierId for /api/v1/orders")
    public void checkOrderListWithCourierIdNull() {

        var requestOrderList = getResponseViaGet(ORDERS_HANDLE);

        checkForStatusCode(requestOrderList, HTTP_OK);
        checkOrdersIdNotNull(requestOrderList);
    }
}
