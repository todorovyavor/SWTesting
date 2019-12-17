package BankAccount;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BankAccountCreation {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final String BULK_RESOURCE_PATH = "/settings/bankaccounts";
    private static final String EMAIL = "todorov_yavor@abv.bg";
    private static final String PASSWORD = "Test123!";
    public String BankAccountId;

    static {
        RestAssured.baseURI = "https://test-399.inv.bg/";
        RestAssured.basePath = "/RESTapi";
        RestAssured.authentication = RestAssured.preemptive().basic(EMAIL, PASSWORD);
    }


    @Test
    public void canCreateNewBankAccount() {
        BankAccount ExpressBankAccount = new BankAccount(
                "Expressbank AD",
                "Експресбанк АД",
                "BG86BNPA94409174864161",
                "BNPABGSX");

        String body = GSON.toJson(ExpressBankAccount);
        Response createResponse = RestAssured
                .given()
                .body(body)
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .post(BULK_RESOURCE_PATH);

        createResponse.prettyPrint();

        BankAccountId = JsonPath.from(createResponse.getBody().asString()).getString("success.id");
        System.out.println("The id is " + BankAccountId);
    }


    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://inv.bg/");
    }

    @Test
    public void loginToHomepage() {
        driver.findElement(By.xpath("//*[@id=\"inheader\"]/a[2]")).click();
        driver.findElement(By.name("subdomain")).sendKeys("test-399");
        driver.findElement(By.id("gologin")).click();
        driver.findElement(By.name("username")).sendKeys("todorov_yavor@abv.bg");
        driver.findElement(By.name("password")).sendKeys("Test123!");
        driver.findElement(By.id("loginsubmit")).click();
    }

    @Test
    public void goToBankAccounts() {
        driver.findElement(By.xpath("//*[@id=\"toplinks\"]/a[4]/b")).click();
        driver.findElement(By.xpath("//*[@id=\"whiterow2\"]/div[2]/div[1]/div[1]/a[2]")).click();
    }

    @Test
    public void deleteBankAccount() {
        driver.findElement(By.xpath("//a[contains(@href,'BankAccountId')]")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div/button[1]")).click();


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }

}

