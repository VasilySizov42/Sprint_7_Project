package Example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static Example.DataForTesting.YANDEX_SCOOTER;
import static Example.MethodFactory.*;

public class CourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = YANDEX_SCOOTER;
    }

    @Test
    @DisplayName("Check order list wdhgfgithout CourierId of /api/v1/orders")
    @Description("Basic test for /api/v1/orders")
    public void courier() {
        var courier = genericCourier();

        var response = createCourier(courier);

        assertCreatedSuccessfully(response);

        var credentials = Credentials.from(courier);

        loggedInSuccessfully(credentials);

        deleteCourier(courier);

    }
}
