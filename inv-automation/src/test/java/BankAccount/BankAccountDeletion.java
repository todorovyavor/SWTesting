package BankAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BankAccountDeletion {

    private WebDriver driver;

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
        public void deleteBankAccount(){
        driver.findElement(By.xpath("//a[contains(@href,'9')]")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div/button[1]")).click();


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}

