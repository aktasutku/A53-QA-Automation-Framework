import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
//    @Parameters({"BaseUrl"})
//    @Test
//    public void navigateToKoelApp(String BaseUrl) {
//        navigateToUrl(BaseUrl);
//        Assert.assertEquals(driver.getCurrentUrl(),BaseUrl);
//        driver.quit();
//    }

    @Test
    public void loginValidEmailPassword() {
        try{
            provideEmail("utku.aktas94@testpro.io");
            providePassword("ekga9uf6");
            clickSubmit();
            //Assertion
            WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
            Assert.assertTrue(avatarIcon.isDisplayed());
        }catch(Exception e ){
            System.out.println("something went wrong " + e);
        }
    }

    @Test(dataProvider = "InvalidLoginData", dataProviderClass = BaseTest.class)
//    @Parameters({"BaseUrl"})
    public void loginWithInvalidEmailValidPassword(String email, String password) throws InterruptedException {
        provideEmail(email);
        providePassword(password);
        clickSubmit();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }
//
//    @Test
//    public void loginWithInvalidPasswordAndValidEmail() throws InterruptedException {
//        provideEmail("utku.aktas94@testpro.io");
//        providePassword("Invalidpw");
//        clickSubmit();
//        Thread.sleep(2000);
//        Assert.assertEquals(driver.getCurrentUrl(),url);
//    }
}
