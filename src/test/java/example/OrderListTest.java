package example;

import example.methods.CheckinOther;
import example.methods.CheckingResponseForOrder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static example.constants.BaseURLHandlesAndWarnings.ORDERS_HANDLE;
import static example.methods.OtherRequests.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class OrderListTest {

    @Test
    @DisplayName("Check order list")
    @Description("Test for getting a general list of orders without CourierId for /api/v1/orders")
    public void checkOrderListWithCourierIdNull() {

        var requestOrderList = getResponseViaGet(ORDERS_HANDLE);

        CheckinOther.checkForStatusCode(requestOrderList, HTTP_OK);
        CheckingResponseForOrder.checkOrdersIdNotNull(requestOrderList);
    }
}
