package BankAccount;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankAccountVerification {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final String BULK_RESOURCE_PATH = "/settings/bankaccounts";
    private static final String EMAIL = "todorov_yavor@abv.bg";
    private static final String PASSWORD = "Test123!";

    static {
        RestAssured.baseURI = "https://test-399.inv.bg/";
        RestAssured.basePath = "/RESTapi";
        RestAssured.authentication = RestAssured.preemptive().basic(EMAIL, PASSWORD);
    }

    @Test
    public void getAllBankAccounts() {
        Response getResponse = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(BULK_RESOURCE_PATH);

        getResponse.prettyPrint();

        Assertions.assertEquals(200, getResponse.getStatusCode());
    }

}
