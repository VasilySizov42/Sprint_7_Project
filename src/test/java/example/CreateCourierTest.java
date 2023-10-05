package example;

import example.methods.CheckinOther;
import example.methods.CheckingResponseForCourier;
import example.methods.SendingRequestsForCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static example.constants.BaseURLHandlesAndWarnings.ALREADY_IN_USE;
import static example.constants.BaseURLHandlesAndWarnings.NOT_ENOUGH_DATA;
import static java.net.HttpURLConnection.*;

public class CreateCourierTest {
    Courier courierData;
    @Before
    public void createNewCourierData() {
        courierData = SendingRequestsForCourier.genericCourier();
    }
    @Test
    @DisplayName("Check creating a courier with random name")
    @Description("Basic test for /api/v1/courier")
    public void createCourierWithRandomName() {
        var courier = SendingRequestsForCourier.createCourier(courierData);

        CheckinOther.checkForStatusCode(courier, HTTP_CREATED);
        CheckingResponseForCourier.checkCreatedWithOkTrue(courier);
    }
    @Test
    @DisplayName("Check creating a courier with a duplicate name")
    @Description("Attempt to create a courier of a previously used name for /api/v1/courier")
    public void creatingCourierWithDuplicateName() {
        var courier = SendingRequestsForCourier.createCourier(courierData);

        CheckinOther.checkForStatusCode(courier, HTTP_CREATED);
        CheckingResponseForCourier.checkCreatedWithOkTrue(courier);

        var courier2 = SendingRequestsForCourier.createCourier(courierData);

        CheckinOther.checkForStatusCode(courier2, HTTP_CONFLICT);
        CheckinOther.checkParamWithValue(courier2, "message", ALREADY_IN_USE);
    }
    @Test
    @DisplayName("Check creating a courier without name")
    @Description("Attempt to create a courier without login name for /api/v1/courier")
    public void creatingCourierWithoutName() {
        courierData.setLogin(null);
        var courier = SendingRequestsForCourier.createCourier(courierData);

        CheckinOther.checkForStatusCode(courier, HTTP_BAD_REQUEST);
        CheckinOther.checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
    @Test
    @DisplayName("Check creating a courier without name")
    @Description("Attempt to create a courier without login name for /api/v1/courier")
    public void creatingCourierWithBlankName() {
        courierData.setLogin("");
        var courier = SendingRequestsForCourier.createCourier(courierData);

        CheckinOther.checkForStatusCode(courier, HTTP_BAD_REQUEST);
        CheckinOther.checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
    @Test
    @DisplayName("Check creating a courier without password")
    @Description("Attempt to create a courier without password for /api/v1/courier")
    public void creatingCourierWithoutPassword() {
        courierData.setPassword(null);
        var courier = SendingRequestsForCourier.createCourier(courierData);

        CheckinOther.checkForStatusCode(courier, HTTP_BAD_REQUEST);
        CheckinOther.checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
    @Test
    @DisplayName("Check creating a courier without password")
    @Description("Attempt to create a courier without password for /api/v1/courier")
    public void creatingCourierWithBlankPassword() {
        courierData.setPassword("");
        var courier = SendingRequestsForCourier.createCourier(courierData);

        CheckinOther.checkForStatusCode(courier, HTTP_BAD_REQUEST);
        CheckinOther.checkParamWithValue(courier, "message", NOT_ENOUGH_DATA);
    }
    @After
    public void deleteCreatedCourier() {
        try {
            var courierDelete = SendingRequestsForCourier.deleteCourier(courierData);
            CheckinOther.checkForStatusCode(courierDelete, HTTP_OK);
            CheckingResponseForCourier.checkCreatedWithOkTrue(courierDelete);
        }
        catch (Exception e){
            System.out.println("Курьер не был создан. Проверьте данные.");
        }
    }
}
