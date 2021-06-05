package pagesObjects;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Delete {
    private static WebDriver driver;
    private static final By emailTxt = By.id("Email");
    private static final By passwordTxt = By.id("Password");
    private static final By loginButton = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
    private static final By logoutLink = By.linkText("Logout");
    public static By errorMessage = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]");


    @Test
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drives/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://admin-demo.nopcommerce.com/login");
        setEmailTxt("admin@yourstore.com");
        setPasswordTxt("admisn");
        clickLogin();
        Thread.sleep(3000);
        String exptitle = "Dashboard / nopCommerce administration";
        /*
        ** successful <--------------------
        if (driver.getTitle().equals("Dashboard / nopCommerce administration")) {
            clickLogout();
            driver.close();
        }
        else {
            System.out.println(getError());
        }

         */
/*
        if(driver.getPageSource().contains("Login was unsuccessful"))
        {
            driver.close();
            Assert.assertThrows(AssertionError.class, Assert::fail);
            System.out.println("closed");


        }
        else
        {
            assertEquals(exptitle, driver.getTitle());
            System.out.println(driver.getTitle());
        }
  */
    }

    public static void setEmailTxt(String email) {
        driver.findElement(emailTxt).clear();
        driver.findElement(emailTxt).sendKeys(email);
    }

    public static void setPasswordTxt(String password) {
        driver.findElement(passwordTxt).clear();
        driver.findElement(passwordTxt).sendKeys(password);
    }

    public static void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public static void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public static String getError() {
        if (driver.findElement(errorMessage).isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return driver.findElement(errorMessage).getText();
        } else
            return "";
    }
}
