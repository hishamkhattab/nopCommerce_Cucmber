package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddCustomerPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    /**
     * The Locators
     **/

    private final By customersList = By.cssSelector("body > div.wrapper > aside > div > div.os-padding > div > div > nav > ul > li:nth-child(4) > a");
    private final By customersItem = By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/ul/li[1]/a");
    private final By addNewButton = By.cssSelector("body > div.wrapper > div.content-wrapper > form:nth-child(2) > div > div > a");
    private final By saveButton = By.xpath("/html/body/div[3]/div[1]/form/div[1]/div/button[1]");
    private final By emailTxt = By.id("Email");
    private final By passwordTxt = By.id("Password");
    private final By firstNameTxt = By.id("FirstName");
    private final By lastNameTxt = By.id("LastName");
    private final By maleGenderRadio = By.id("Gender_Male");
    private final By femaleGenderRadio = By.id("Gender_Female");
    private final By dateOfBirthTxt = By.id("DateOfBirth");
    private final By CompanyNameTxt = By.id("Company");
    private final By isTaxExemptCheck = By.id("IsTaxExempt");
    private final By newsLetterYourStoreName = /*By.cssSelector("#SelectedNewsletterSubscriptionStoreIds_taglist > li");*/By.xpath("//li[contains(text(),'Your store name')]");
    private final By newsLetterTestStore = /*By.cssSelector("#SelectedNewsletterSubscriptionStoreIds_taglist > li");*/By.xpath("//li[contains(text(),'Test store 2')]");
    private final By newsLetterTxt = By.xpath("//*[@id=\"customer-info\"]/div[2]/div[9]/div[2]/div/div[1]/div/div");
    private final By txtCustomerRoles = By.xpath("//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div");
    private final By listRoleRegistered = By.xpath("//li[contains(text(),'Registered')]");
    private final By listRoleAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
    private final By listRoleForumModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
    private final By listRoleGuests = By.xpath("//li[contains(text(),'Guests')]");
    private final By listRoleVendors = By.xpath("//li[contains(text(),'Vendors')]");
    private final By vendorList = By.id("VendorId");
    private By activeCheck = By.id("Active");
    private final By adminCommentTxt = By.id("AdminComment");


    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    /****************
     * Action Methods
     *****************/

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void clickCustomersList() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(customersList)));
        driver.findElement(customersList).click();
    }

    public void clickCustomersItem() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(customersItem)));
        driver.findElement(customersItem).click();
    }

    public void clickAddNewButton() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(addNewButton)));
        driver.findElement(addNewButton).click();
    }

    public void setEmailTxt(String email) {
        driver.findElement(emailTxt).sendKeys(email);
    }

    public void setPasswordTxt(String password) {
        driver.findElement(passwordTxt).sendKeys(password);
    }

    public void setFirstNameTxt(String firstName) {
        driver.findElement(firstNameTxt).sendKeys(firstName);
    }

    public void setLastNameTxt(String lastName) {
        driver.findElement(lastNameTxt).sendKeys(lastName);
    }

    public void setGender(String gender) {
        if (gender.equals("Male"))
            driver.findElement(maleGenderRadio).click();

        else if (gender.equals("Female"))
            driver.findElement(femaleGenderRadio).click();
        else
            driver.findElement(maleGenderRadio).click();
    }


    public void setDateOfBirthTxt(String dateOfBirth) {
        driver.findElement(dateOfBirthTxt).sendKeys(dateOfBirth);
    }

    public void setCompanyNameTxt(String companyName) {
        driver.findElement(CompanyNameTxt).sendKeys(companyName);
    }

    public void clickIsTaxExemptCheck() {
        driver.findElement(isTaxExemptCheck).click();
    }


    public void setNewsLetter(String value) {
        WebElement letter;

        driver.findElement(newsLetterTxt).click();

        if (value.equals("Test store 2")) {
            letter = driver.findElement(newsLetterTestStore);
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(newsLetterTestStore)));
        } else {
            letter = driver.findElement(newsLetterYourStoreName);
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(newsLetterYourStoreName)));
        }
        letter.click();

    }

    public void setCustomerRole(String role) throws InterruptedException {
        if (!role.equals("vendors")) {
            driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }
        driver.findElement(txtCustomerRoles).click();

        WebElement listItems;

        if (role.equals("Administrators")) {
            listItems = driver.findElement(listRoleAdministrators);
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(listRoleAdministrators)));
        } else if (role.equals("Guests")) {
            listItems = driver.findElement(listRoleGuests);
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(listRoleGuests)));
        } else if (role.equals("Vendors")) {
            listItems = driver.findElement(listRoleVendors);
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(listRoleVendors)));
        } else if (role.equals("Registered")) {
            listItems = driver.findElement(listRoleRegistered);
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(listRoleRegistered)));
        } else {
            listItems = driver.findElement(listRoleForumModerators);
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(listRoleForumModerators)));
        }

        listItems.click();
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("argument[0].click();",listItems);

    }

    public void setActiveCheck(By activeCheck) {
        this.activeCheck = activeCheck;
    }


    public void setManagerVendor(String value) {
        Select vendor = new Select(driver.findElement(vendorList));
        vendor.selectByVisibleText(value);
    }

    public void checkActive() {
        driver.findElement(activeCheck).click();
    }

    public void setComment(String text) {
        driver.findElement(adminCommentTxt).sendKeys(text);
    }
}
