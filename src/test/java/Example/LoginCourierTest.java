package Example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static Example.DataForTesting.ACCOUNT_NOT_FOUND;
import static Example.DataForTesting.INSUFFICIENT_LOGIN_DATA;
import static Example.MethodFactory.*;
import static java.net.HttpURLConnection.*;

public class LoginCourierTest {

    @Test
    @DisplayName("Check login a courier with random name")
    @Description("Attempt to login a courier with random name for /api/v1/courier/login")
    public void loginCourierWithRandomName() {
        var courierData = genericCourier();
        createCourier(courierData);

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_OK);
        checkLoggedInWithNotNullId(credentials);

        var courierDelete = deleteCourier(courierData);
        checkForStatusCode(courierDelete, HTTP_OK);
        checkCreatedWithOkTrue(courierDelete);
    }
    @Test
    @DisplayName("Check login a courier without name")
    @Description("Attempt to login a courier without login name for /api/v1/courier/login")
    public void loginCourierWithoutName() {
        var courierData = genericCourier();
        createCourier(courierData);
        var name = courierData.getLogin();
        courierData.setLogin(null);

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_BAD_REQUEST);
        checkParamWithValue(credentials, "message", INSUFFICIENT_LOGIN_DATA);

        courierData.setLogin(name);
        var courierDelete = deleteCourier(courierData);
        checkForStatusCode(courierDelete, HTTP_OK);
        checkCreatedWithOkTrue(courierDelete);
    }
    @Test
    @DisplayName("Check login a courier with blank name")
    @Description("Attempt to login a courier with blank login name for /api/v1/courier/login")
    public void loginCourierWithBlankName() {
        var courierData = genericCourier();
        var name = courierData.getLogin();
        createCourier(courierData);
        courierData.setLogin("");

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_BAD_REQUEST);
        checkParamWithValue(credentials, "message", INSUFFICIENT_LOGIN_DATA);

        courierData.setLogin(name);
        var courierDelete = deleteCourier(courierData);
        checkForStatusCode(courierDelete, HTTP_OK);
        checkCreatedWithOkTrue(courierDelete);
    }
    @Test
    @DisplayName("Check login a courier without password")
    @Description("Attempt to login a courier without password for /api/v1/courier/login")
    public void loginCourierWithoutPassword() {
        var courierData = genericCourier();
        createCourier(courierData);
        var pass = courierData.getPassword();
        courierData.setPassword(null);

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_BAD_REQUEST);
        checkParamWithValue(credentials, "message", INSUFFICIENT_LOGIN_DATA);

        courierData.setPassword(pass);
        var courierDelete = deleteCourier(courierData);
        checkForStatusCode(courierDelete, HTTP_OK);
        checkCreatedWithOkTrue(courierDelete);
    }
    @Test
    @DisplayName("Check login a courier with blank password")
    @Description("Attempt to login a courier with blank password for /api/v1/courier/login")
    public void loginCourierWithBlankPassword() {
        var courierData = genericCourier();
        createCourier(courierData);
        var pass = courierData.getPassword();
        courierData.setPassword("");

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_BAD_REQUEST);
        checkParamWithValue(credentials, "message", INSUFFICIENT_LOGIN_DATA);

        courierData.setPassword(pass);
        var courierDelete = deleteCourier(courierData);
        checkForStatusCode(courierDelete, HTTP_OK);
        checkCreatedWithOkTrue(courierDelete);
    }
    @Test
    @DisplayName("Check login a courier with wrong name")
    @Description("Attempt to login a courier with wrong login name for /api/v1/courier/login")
    public void loginCourierWithWrongName() {
        var courierData = genericCourier();

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_NOT_FOUND);
        checkParamWithValue(credentials, "message", ACCOUNT_NOT_FOUND);
    }
    @Test
    @DisplayName("Check login a courier with wrong password")
    @Description("Attempt to login a courier with wrong password for /api/v1/courier/login")
    public void loginCourierWithWrongPassword() {
        var courierData = genericCourier();

        var credentialsData = Credentials.from(courierData);

        var credentials = loggedInCourier(credentialsData);

        checkForStatusCode(credentials, HTTP_NOT_FOUND);
        checkParamWithValue(credentials, "message", ACCOUNT_NOT_FOUND);
    }
}
