import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    //Data Providers
    @DataProvider(name = "InvalidLoginData")
    public static Object[][] getDataFromDataProviders(){
        return new Object[][]{
                {"invalid@mail.com" , "invalidPassword"},
                {"utku.aktas94@testpro.io" , ""},
                {"" , "ekga9uf6"},
                {"" , ""},
        };
    }

    public WebDriver driver;
    public String url = "https://qa.koel.app/";
//Test
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameters({"BaseUrl"})
    @BeforeMethod
    public void launchBrowser(String BaseUrl){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Manage browser - wait for 10 seconds before failing/quitting
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        navigateToUrl(BaseUrl);
    }

    @AfterMethod
    public void closeBrowser(){driver.quit();}

    void provideEmail(String email){
        WebElement emailField =  driver.findElement((By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    void providePassword(String password){
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    void clickSubmit(){
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }


    public void navigateToUrl(String url){
        driver.get(url);
    }
}
//Hello Test Commitments