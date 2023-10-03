package Example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static Example.DataForTesting.YANDEX_SCOOTER;
import static Example.MethodFactory.*;
import static java.net.HttpURLConnection.*;

public class CreateCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = YANDEX_SCOOTER;
    }

    @Test
    @DisplayName("Check creating a courier with random name")
    @Description("Basic test for /api/v1/courier")
    public void createCourierWithRandomName() {
        var courierData = genericCourier();

        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_CREATED);
        checkCreatedWithOkTrue(courier);

        var courierDelete = deleteCourier(courierData);
        checkForStatusCode(courierDelete, HTTP_OK);
        checkCreatedWithOkTrue(courierDelete);
    }
    @Test
    @DisplayName("Check creating a courier with a duplicate name")
    @Description("Attempt to create a courier of a previously used name for /api/v1/courier")
    public void creatingCourierWithDuplicateName() {
        var courierData = genericCourier();

        var courier = createCourier(courierData);
        var courier2 = createCourier(courierData);

        checkForStatusCode(courier, HTTP_CREATED);
        checkCreatedWithOkTrue(courier);
        checkForStatusCode(courier2, HTTP_CONFLICT);
        checkParamWithValue(courier2, "message", "Этот логин уже используется. Попробуйте другой.");
    }
    @Test
    @DisplayName("Check creating a courier without name")
    @Description("Attempt to create a courier without login name for /api/v1/courier")
    public void creatingCourierWithoutName() {
        var courierData = genericCourier();
        courierData.setLogin(null);
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", "Недостаточно данных для создания учетной записи");
    }
    @Test
    @DisplayName("Check creating a courier without name")
    @Description("Attempt to create a courier without login name for /api/v1/courier")
    public void creatingCourierWitBlankName() {
        var courierData = genericCourier();
        courierData.setLogin("");
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", "Недостаточно данных для создания учетной записи");
    }
    @Test
    @DisplayName("Check creating a courier without password")
    @Description("Attempt to create a courier without password for /api/v1/courier")
    public void creatingCourierWitoutPassword() {
        var courierData = genericCourier();
        courierData.setPassword(null);
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", "Недостаточно данных для создания учетной записи");
    }
    @Test
    @DisplayName("Check creating a courier without password")
    @Description("Attempt to create a courier without password for /api/v1/courier")
    public void creatingCourierWitBlankPassword() {
        var courierData = genericCourier();
        courierData.setPassword("");
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", "Недостаточно данных для создания учетной записи");
    }
}
