package BaseClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import PageClasses.GovernmentPage;
import PageClasses.WebDevelopmentPage;

public class HomePage extends BasePageClass {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ================= Elements =================

    @FindBy(xpath = "//div[@class='react-autosuggest__container']/input[@class='react-autosuggest__input']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='nostyle search-button']/div[@class='magnifier-wrapper']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@role='navigation']//ul/li[4]//a")
    private WebElement governmentLink;

    // ================= Actions =================
    //Web Development link
	@FindBy(xpath="//*/input[@type='text' and @role='textbox']")
	public WebElement searchBox;
	
	@FindBy(xpath="//button[contains(@class,'nostyle search-button')]/div")
	public WebElement searchClick;
	
	public WebDevelopmentPage searchCourse()
	{
		
		searchBox.sendKeys("web development");
		searchClick.click(); 
		
		return PageFactory.initElements(driver, WebDevelopmentPage.class);
	}

    // Navigate to Government Page
    public GovernmentPage goToGovernmentPage() {
        wait.until(ExpectedConditions.elementToBeClickable(governmentLink));
        governmentLink.click();

        return PageFactory.initElements(driver, GovernmentPage.class);
    }
}
