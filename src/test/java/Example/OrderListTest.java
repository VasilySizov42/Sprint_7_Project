package Example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import io.qameta.allure.Description; // импорт Description
import io.qameta.allure.Step; // импорт Step
import io.qameta.allure.*;

import static Example.DataForTesting.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
public class OrderListTest {
    @Before
    public void setUp() {
        // повторяющуюся для разных ручек часть URL лучше записать в переменную в методе Before
        // если в классе будет несколько тестов, указывать её придётся только один раз
        RestAssured.baseURI = YANDEX_SCOOTER;
    }

    @Test
    @DisplayName("Check order list without CourierId of /api/v1/orders") // имя теста
    @Description("Basic test for /api/v1/orders") // описание теста
    public void checkOrderListWithCourierIdNull() {

        Response response = getResponse();

        extracted(response);

        extracted1(response);
    }

    @Step ("3")
    public void extracted1(Response response) {
        response.then().assertThat().body("orders[0].id", notNullValue());
        //response.then().assertThat().body("orders[1].id", equalTo(37));
        System.out.println(response.body().asString());
    }

    @Step ("2")
    public void extracted(Response response) {
        response.then().statusCode(200);
        System.out.println(response.statusCode());
    }

    @Step ("1")
    public Response getResponse() {
        Response response = given().log().all()
                .when().get(BASIC_HANDLE+ORDERS_HANDLE);
        return response;
    }
}
