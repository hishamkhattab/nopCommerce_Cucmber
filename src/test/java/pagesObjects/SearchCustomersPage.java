package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.Locale;

public class SearchCustomersPage {


    public SearchCustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WaitHelper(driver);
    }


    private final WebDriver driver;
    private final WaitHelper wait;

    /*********
     * Locator
     * ********/
    private final By searchEmailTxt = By.id("SearchEmail");
    private final By searchFirstNameTxt = By.id("SearchFirstName");
    private final By searchLastNameTxt = By.id("SearchLastName");
    private final By searchButton = By.id("search-customers");
    private final By emailResult = By.xpath("//*[@id=\"customers-grid\"]/tbody/tr/td[2]");
    private final By nameResult = By.xpath("//*[@id=\"customers-grid\"]/tbody/tr/td[3]");

    public void setEmail(String email) {
        WebElement emailTxt = driver.findElement(searchEmailTxt);
        wait.waitForElement(emailTxt, 5);
        emailTxt.clear();
        emailTxt.sendKeys(email);
    }

    public void setFirstName(String name) {
        WebElement nameTxt = driver.findElement(searchFirstNameTxt);
        wait.waitForElement(nameTxt, 5);
        nameTxt.clear();
        nameTxt.sendKeys(name);
    }

    public void setLastName(String name) {
        WebElement nameTxt = driver.findElement(searchLastNameTxt);
        wait.waitForElement(nameTxt, 5);
        nameTxt.clear();
        nameTxt.sendKeys(name);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public String getEmail() {
        return driver.findElement(emailResult).getText();
    }

    public String getFullName() {
        return driver.findElement(nameResult).getText().toLowerCase(Locale.ROOT);
    }


}
