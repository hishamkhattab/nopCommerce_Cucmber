package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final By emailTxt = By.id("Email");
    private final By passwordTxt = By.id("Password");
    private final By loginButton = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
    private final By logoutLink = By.linkText("Logout");
    public By errorMessage = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setEmailTxt(String email) {
        driver.findElement(emailTxt).clear();
        driver.findElement(emailTxt).sendKeys(email);
    }

    public void setPasswordTxt(String password) {
        driver.findElement(passwordTxt).clear();
        driver.findElement(passwordTxt).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public String getError() {
        if (driver.findElement(errorMessage).isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return driver.findElement(errorMessage).getText();
        } else
            return "";
    }
}
