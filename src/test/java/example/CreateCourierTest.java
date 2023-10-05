package example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static example.DataForTesting.ALREADY_IN_USE;
import static example.DataForTesting.NOT_ENOUGH_DATA;
import static example.MethodFactory.*;
import static java.net.HttpURLConnection.*;

public class CreateCourierTest {

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

        checkForStatusCode(courier, HTTP_CREATED);
        checkCreatedWithOkTrue(courier);

        var courier2 = createCourier(courierData);

        checkForStatusCode(courier2, HTTP_CONFLICT);
        checkParamWithValue(courier2, "message", ALREADY_IN_USE);
    }
    @Test
    @DisplayName("Check creating a courier without name")
    @Description("Attempt to create a courier without login name for /api/v1/courier")
    public void creatingCourierWithoutName() {
        var courierData = genericCourier();
        courierData.setLogin(null);
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
    @Test
    @DisplayName("Check creating a courier without name")
    @Description("Attempt to create a courier without login name for /api/v1/courier")
    public void creatingCourierWithBlankName() {
        var courierData = genericCourier();
        courierData.setLogin("");
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
    @Test
    @DisplayName("Check creating a courier without password")
    @Description("Attempt to create a courier without password for /api/v1/courier")
    public void creatingCourierWithoutPassword() {
        var courierData = genericCourier();
        courierData.setPassword(null);
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
    @Test
    @DisplayName("Check creating a courier without password")
    @Description("Attempt to create a courier without password for /api/v1/courier")
    public void creatingCourierWithBlankPassword() {
        var courierData = genericCourier();
        courierData.setPassword("");
        var courier = createCourier(courierData);

        checkForStatusCode(courier, HTTP_BAD_REQUEST);
        checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
}
