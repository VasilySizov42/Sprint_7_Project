package Example;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static Example.DataForParametrization.*;
import static Example.DataForTesting.YANDEX_SCOOTER;
import static Example.MethodFactory.*;
import static java.net.HttpURLConnection.HTTP_CREATED;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;


    public CreateOrderTest(String firstName, String lastName,
                           String address, int metroStation,
                           String phone, int rentTime,
                           String deliveryDate, String comment,
                           String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Before
    public void setUp() {
            RestAssured.baseURI = YANDEX_SCOOTER;
    }
    @Parameterized.Parameters(name = "Цвет самоката. Тестовые данные: {8} {9}")
    public static Object[][] params() {
        return new Object[][]{BLACK_SCOOTER, GRAY_SCOOTER,
                BLACK_AND_GRAY_SCOOTER, UNDEFINED_COLOUR_SCOOTER,};
    }
    @Test
    public void checkBlackScooterColourTest() {
    var order = createOrder(params);
        checkForStatusCode(order, HTTP_CREATED);
        checkCreatedWithTrackNotNull(order);
    }
}