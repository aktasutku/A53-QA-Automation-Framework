package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //Element Locators using Page Factory
    @FindBy(css = "type='email'")
    WebElement emailTextField;

    @FindBy(css = "type='password'")
    WebElement passwordField;

    @FindBy(css = "[type='submit']")
    WebElement loginButton;

    // continue here 1.06.34


    //Element Locators

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitButton = By.cssSelector("button[type='submit']");

    //Page Methods
    public void provideEmail(String email){
        findElementUsingByLocator(emailField).sendKeys(email);
    }
    public void providePassword(String password){
        findElementUsingByLocator(passwordField).sendKeys(password);
    }

    public void clickSubmit(){
        findElementUsingByLocator(submitButton).click();
    }

    public void login(){
        provideEmail("utku.aktas94@testpro.io");
        providePassword("ekga9uf6");
        clickSubmit();
    }
}
