package Item;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final String BULK_RESOURCE_PATH ="/items";
    public static final String RESOURCE_PATH ="/item";
    private static final String EMAIL = "todorov_yavor@abv.bg";
    private static final String PASSWORD = "Test123!";

    static{
        RestAssured.baseURI="https://test-399.inv.bg/";
        RestAssured.basePath="/RESTapi";
        RestAssured.authentication=RestAssured.preemptive().basic(EMAIL,PASSWORD);
    }

    @Test
    public void getAllItems() {
        Response getResponse = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(BULK_RESOURCE_PATH);

        getResponse.prettyPrint();

        Assertions.assertEquals(200, getResponse.getStatusCode());
    }


    @Test
    public void canCreateNewItem() {
        Item coffeeItem = new Item("Coffaa,","20", "кг.", "10");
        String body = GSON.toJson(coffeeItem);
        Response createResponse = RestAssured
                .given()
                .body(body)
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .post(RESOURCE_PATH);

        createResponse.prettyPrint();

                String itemId = JsonPath.from(createResponse.getBody().asString()).getString("success.id");
        System.out.println("The id is " + itemId);
    }
    @Test
    public void deleteItem() {
        Response deleteResponse = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(RESOURCE_PATH + "/" + "6");
        Assertions.assertEquals(200,deleteResponse.getStatusCode());
        deleteResponse.prettyPrint();
    }
    }


