import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

//Test script using page object model
//Locators used in these test were located using By abstract class
//@author: Utku Aktas
//
public class LoginTests extends BaseTest{
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    @Test
    public void loginWithCorrectCredentials(){
        loginPage.login();

        Assert.assertTrue(homePage.getUserAvatarIcon().isDisplayed());
    }

    @Test
    public void loginInWithCorrectCredentials(){
        loginPage.provideEmail("AAutku.aktas94@testpro.io");
        loginPage.providePassword("AAekga9uf6");
        loginPage.clickSubmit();

        Assert.assertTrue(homePage.getUserAvatarIcon().isDisplayed());
    }
}
