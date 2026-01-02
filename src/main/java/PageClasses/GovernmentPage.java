package PageClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import BaseClasses.BasePageClass;

public class GovernmentPage extends BasePageClass {

	WebDriverWait wait;

	public GovernmentPage(WebDriver driver) {
		 this.driver = driver;  
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));  

	}

	// ---------- Form Fields ---------- //

	@FindBy(xpath = "//input[@id='FirstName']")
	public WebElement firstNameInput;

	@FindBy(xpath = "//input[@id='LastName']")
	public WebElement lastNameInput;

	@FindBy(xpath = "//input[@id='Email']")
	public WebElement workEmailInput;

	@FindBy(xpath = "//input[@id='Phone']")
	public WebElement phoneNOInput;

	@FindBy(xpath = "//select[@id='rentalField9']")
	public WebElement organizationTypeDropdown;

	@FindBy(xpath = "//input[@id='Title']")
	public WebElement jobTitleInput;

	@FindBy(xpath = "//input[@id='Company']")
	public WebElement organizationName;

	@FindBy(xpath = "//select[@id='Employee_Range__c']")
	public WebElement organizationSizeDropdown;

	@FindBy(xpath = "//select[@id='What_the_lead_asked_for_on_the_website__c']")
	public WebElement needDropdown;

	@FindBy(xpath = "//select[@id='Self_reported_employees_to_buy_for__c']")
	public WebElement expectedLearnersInput;

	@FindBy(xpath = "//textarea[@id='rentalField5']")
	public WebElement describeNeedInput;

	@FindBy(xpath = "//select[@id='Country']")
	public WebElement countryDropdown;

	@FindBy(xpath = "//select[@id='State']")
	public WebElement stateDropdown;

	@FindBy(xpath = "//span//button[@type='submit']")
	public WebElement requestInfoButton;

	// ---------- Error Messages ---------- //

	@FindBy(xpath = "//div[@id='ValidMsgState']")
	public WebElement stateRequiredError;

	@FindBy(xpath = "//div[@id='ValidMsgEmail']")
	public WebElement invalidEmailError;
	

	// ---------- Methods ---------- //

	// Scroll to the Info Button
	public void scrollToForm() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", requestInfoButton);
	}

	
	public void enterFirstName(String firstName) {
		wait.until(ExpectedConditions.visibilityOf(firstNameInput)).clear();
		firstNameInput.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		wait.until(ExpectedConditions.visibilityOf(lastNameInput)).clear();
		lastNameInput.sendKeys(lastName);
	}

	public void enterWorkEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(workEmailInput)).clear();
		workEmailInput.sendKeys(email);
	}

	public void enterPhoneNumber(String phone) {
		wait.until(ExpectedConditions.visibilityOf(phoneNOInput)).clear();
		phoneNOInput.sendKeys(phone);
	}

	public void enterJobTitle(String jobTitle) {
		wait.until(ExpectedConditions.visibilityOf(jobTitleInput)).clear();
		jobTitleInput.sendKeys(jobTitle);
	}

	public void enterOrganizationName(String orgName) {
		wait.until(ExpectedConditions.visibilityOf(organizationName)).clear();
		organizationName.sendKeys(orgName);
	}

	public void enterDescribeNeed(String description) {
	    wait.until(ExpectedConditions.elementToBeClickable(describeNeedInput));
	    describeNeedInput.clear();
	    describeNeedInput.sendKeys(description);
	}


	// Dropdown
	public void selectOrganizationType(String type) {
	    wait.until(ExpectedConditions.elementToBeClickable(organizationTypeDropdown));
	    Select dropdown = new Select(organizationTypeDropdown);
	    dropdown.selectByVisibleText(type);
	}

	public void selectOrganizationSize(String size) {
	    wait.until(ExpectedConditions.visibilityOf(organizationSizeDropdown));
	    Select dropdown = new Select(organizationSizeDropdown);
	    dropdown.selectByVisibleText(size);
	}

	public void selectNeed(String need) {
	    wait.until(ExpectedConditions.elementToBeClickable(needDropdown));
	    Select dropdown = new Select(needDropdown);
	    dropdown.selectByVisibleText(need);
	}

	public void selectExpectedLearners(String number) {
	    wait.until(ExpectedConditions.visibilityOf(expectedLearnersInput));
	    Select dropdown = new Select(expectedLearnersInput);
	    dropdown.selectByVisibleText(number);
	}

	public void selectCountry(String country) {
	    wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
	    Select dropdown = new Select(countryDropdown);
	    dropdown.selectByVisibleText(country);
	}

	public void selectState(String state) {
	    wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
	    Select dropdown = new Select(stateDropdown);
	    dropdown.selectByVisibleText(state);
	}


	// Click Request Info button
	public void clickRequestInfo() {
		wait.until(ExpectedConditions.elementToBeClickable(requestInfoButton)).click();
	}

	// state validation error
	public void submitFormAndCaptureStateError() throws IOException  {
	    try {
	        clickRequestInfo(); 

	        WebElement stateError = wait.until(
	            ExpectedConditions.visibilityOf(stateRequiredError)
	        );

	        takeScreenShot();
	        System.out.println("State error displayed: " + stateError.getText());

	    } catch (Exception e) {
	        takeScreenShot();
	        throw new AssertionError("State validation failed: " + e.getMessage());
	    } finally {
	        driver.quit();
	    }
	}


	// Screenshot
	public void takeScreenShot() throws IOException {
		String filePath = System.getProperty("user.dir") + "/Screenshots/" + generateFile();
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(filePath));
	}

	public String generateFile() {
		return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".png";
	}

}
