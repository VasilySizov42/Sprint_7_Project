package Example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static Example.DataForTesting.YANDEX_SCOOTER;
import static Example.MethodFactory.*;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class CourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = YANDEX_SCOOTER;
    }

    @Test
    @DisplayName("Check order list wdhgfgithout CourierId of /api/v1/orders")
    @Description("Basic test for /api/v1/orders")
    public void courier() {
        var courierData = genericCourier();

        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_CREATED);
        checkCreatedWithOkTrue(courier);

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_OK);
        checkLoggedInWithNotNullId(credentials);

        var courierDelete = deleteCourier(courierData);
        checkForStatusCode(courierDelete, HTTP_OK);
        checkCreatedWithOkTrue(courierDelete);
    }
}
