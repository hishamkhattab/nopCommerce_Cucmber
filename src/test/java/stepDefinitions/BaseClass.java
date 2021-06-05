package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pagesObjects.AddCustomerPage;
import pagesObjects.LoginPage;
import pagesObjects.SearchCustomersPage;

import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public LoginPage loginPage;
    public AddCustomerPage addCustomer;
    public SearchCustomersPage search;
    public static Logger logger;
    public Properties configProp;

    /**
     * Create random string
     * to use as password
     *
     * @return random string
     */
    public static String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }
}
