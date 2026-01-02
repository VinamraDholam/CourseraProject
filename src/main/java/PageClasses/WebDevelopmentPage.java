package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebDevelopmentPage {

WebDriver driver;
	
	public WebDevelopmentPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//button[contains(@data-testid,'chip-button-in')]/div[text()='Language']")
	public WebElement langFilter;
	
	@FindBy(xpath="//div[@data-testid='language:English-false']/label/span/span/input")
	public WebElement engCheckBox;
	
	@FindBy(xpath="//div/button/span[text()='View']")
	public WebElement langViewBtn;

	@FindBy(xpath="//button[contains(@data-testid,'chip-button-in')]/div[text()='Level']")
	public WebElement levelFilter;
	
	@FindBy(xpath="//div[@data-testid='productDifficultyLevel:Beginner-false']/label/div/span/span")
	public WebElement beginnerCheckBox;
	
	@FindBy(xpath="//*/div/button/span[text()='View']")
	public WebElement levelViewBtn;
	
	public void webdevelopmentcourses() {
		
		// 1st Filter
		langFilter.click();
		engCheckBox.click();
		langViewBtn.click();

		// 2nd Filter
		levelFilter.click();
		beginnerCheckBox.click();
		levelViewBtn.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		
		System.out.println("Web Development Courses: ");
		List<WebElement> coursesSearched=driver.findElements(By.xpath("//div[@id='searchResults']/div/div/ul/li"));
		for(int i=0;i<coursesSearched.size() && i<2;i++) {
			
			WebElement course=coursesSearched.get(i);
			
			String Cname=course.findElement(By.xpath(".//div[@class='cds-ProductCard-header']/div[2]/a/h3")).getText();
			String Crating=course.findElement(By.xpath(".//div[@class='cds-ProductCard-footer']/div[2]/div/span")).getText();
			String Creview=course.findElement(By.xpath(".//div[@class='cds-ProductCard-footer']/div[2]/div[2]")).getText();
			String Cduration=course.findElement(By.xpath(".//div[@class='cds-ProductCard-footer']/div[3]/p")).getText();
			
			int serial_no=i+1;
			System.out.println(serial_no+")");
			System.out.println("Course Name: "+Cname);
			System.out.println("Course Rating: "+Crating);
			System.out.println("Course Reviews: "+Creview);
			System.out.println("Course Duration: "+Cduration);
			System.out.println();
		}
	}

    // Hover part
	@FindBy(xpath="//*/div/button[@aria-label='Explore our catalog']")
	public WebElement explore_btn;
	
	@FindBy(linkText="Language Learning")
	public WebElement lang_learning;
	
	public LanguageLearning click_language_learning() {
		
		Actions action = new Actions(driver);
		action.moveToElement(explore_btn).build().perform();
		lang_learning.click();
		
		return PageFactory.initElements(driver, LanguageLearning.class);	
	}

}
