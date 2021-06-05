package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagesObjects.AddCustomerPage;
import pagesObjects.LoginPage;
import pagesObjects.SearchCustomersPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Steps extends BaseClass {

    @Before
    public void setup() throws IOException {
        //reading properties
        configProp = new Properties();
        FileInputStream configProfile = new FileInputStream("Config.properties");
        configProp.load(configProfile);

        //added logger
        logger = Logger.getLogger("nopcommerce");
        PropertyConfigurator.configure("Log4j.properties");

        String browser = configProp.getProperty("browser");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromePath"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxPath"));
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Enter valid driver");
        }

    }

    @Given("user launch chrome browser")
    public void user_launch_chrome_browser() {

        logger.info("************** Launching the browser **************");
        loginPage = new LoginPage(driver);

    }

    @When("user opens URl: {string}")
    public void user_opens_u_rl(String url) {
        logger.info("************** Opening the website **************");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("************** Providing login credentials **************");
        loginPage.setEmailTxt(email);
        loginPage.setPasswordTxt(password);
    }

    @When("click on login")
    public void click_on_login() throws InterruptedException {

        logger.info("************** Clicking login button **************");
        loginPage.clickLogin();
        Thread.sleep(3000);
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expTitle) throws InterruptedException {
        logger.info("*********Login Validation starts ***************");
        if (driver.getPageSource().contains("Login was unsuccessful")) {
            logger.info("*********Login Failed ***************");
            driver.quit();
            Assert.assertThrows(AssertionError.class, Assert::fail);
        } else {
            logger.info("*********Login successful ***************");
            Assert.assertEquals(expTitle, driver.getTitle());
        }
        Thread.sleep(5000);
    }

    @When("user click on logout link")
    public void user_click_on_logout_link() {
        loginPage.clickLogout();
        logger.info("************** Click on logout link **************");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")));
    }

    @Then("close browser")
    public void close_browser() {

        logger.info("************** Closing the browser **************");
        driver.quit();
    }

    /*************************************************************************
     ***************** Customer feature step definition **********************
     *************************************************************************/

    @Then("user can view dashboard")
    public void user_can_view_dashboard() {
        addCustomer = new AddCustomerPage(driver);
        logger.info("************** Dashboard page is visible **************");
        String expectedTitle = "Dashboard / nopCommerce administration";
        assertEquals("incorrect text", expectedTitle, addCustomer.getPageTitle());
    }

    @When("user click on customer menu")
    public void user_click_on_customer_menu() {
        addCustomer.clickCustomersList();
    }

    @When("click on customer menu item")
    public void click_on_customer_menu_item() {
        addCustomer.clickCustomersItem();
    }

    @When("click on add new button")
    public void click_on_add_new_button() {
        addCustomer.clickAddNewButton();
    }

    @Then("user can view add new customer page")
    public void user_can_view_add_new_customer_page() {
        String expectedTitle = "Add a new customer / nopCommerce administration";
        assertEquals("incorrect text", expectedTitle, addCustomer.getPageTitle());
        logger.info("************** Add customer page is visible **************");
    }

    @When("user enter customer info")
    public void user_enter_customer_info() throws InterruptedException {


        logger.info("************** Add new customer credentials **************");
        String email = randomString() + "@gmail.com";
        addCustomer.setEmailTxt(email);
        addCustomer.setPasswordTxt("test123");
        addCustomer.setFirstNameTxt("Pavol");
        addCustomer.setLastNameTxt("Lady");
        addCustomer.setGender("Male");
        addCustomer.setDateOfBirthTxt("03/07/1999");
        addCustomer.setCompanyNameTxt("BusyQA");
        addCustomer.clickIsTaxExemptCheck();
        addCustomer.setNewsLetter("Test Store 2");
        addCustomer.setCustomerRole("Guests");
        addCustomer.setManagerVendor("Vendor 2");
        addCustomer.checkActive();
        addCustomer.setComment("This is for testing.........");
    }

    @When("click on save button")
    public void click_on_save_button() {
        logger.info("************** Click save **************");
        addCustomer.clickSaveButton();
    }

    @Then("user can view confirmation message {string}")
    public void user_can_view_confirmation_message(String string) {
        assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains(string));
        logger.info("************** New customer is added successfully **************");
    }


    /*************************************************************************
     ***************** Search by Email step definition **********************
     *************************************************************************/

    @When("enter the customer Email")
    public void enter_the_customer_email() {
        logger.info("************** Enter Email desired to search for **************");
        search = new SearchCustomersPage(driver);
        search.setEmail("admin@yourStore.com");
    }

    @When("click on search button")
    public void click_on_search_button() throws InterruptedException {
        search.clickSearch();
        logger.info("************** Click on search button **************");
        Thread.sleep(5000);
    }

    @Then("user should find Email in the search table")
    public void user_should_find_email_in_the_search_table() {
        String expectedText = "admin@yourStore.com";
        assertEquals("Incorrect text", expectedText, search.getEmail());
        System.out.println(search.getEmail());
        logger.info("************** Search by Email is successful, and the result is matched **************");
    }

    /*************************************************************************
     ***************** Search by Name step definition **********************
     *************************************************************************/

    @When("enter the customer first name")
    public void enter_the_customer_first_name() {
        search = new SearchCustomersPage(driver);
        logger.info("************** Enter First name desired to search for **************");
        search.setFirstName("steve");
    }

    @When("enter the customer last name")
    public void enter_the_customer_last_name() {
        logger.info("************** Enter last name desired to search for **************");
        search.setLastName("gates");
    }

    @Then("user should find name in the search table")
    public void user_should_find_name_in_the_search_table() {
        String expectedText = "steve gates";
        assertEquals("Incorrect text", expectedText, search.getFullName());
        System.out.println(search.getFullName());
        logger.info("************** Search by full name is successful, and the result is matched **************");
    }
}
