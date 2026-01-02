package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import BaseClasses.HomePage;

public class FreeCoursesPage {

    WebDriver driver;
    WebDriverWait wait;
    

    @FindBy(xpath = "//*/div[@class='css-1oiads2']/button[@data-testid='chip-button-inactive']/div[text()='Topic']")
    public WebElement topic;

    @FindBy(xpath = "//div[@class='cds-checkboxAndRadio-labelText']/span/span[text()='Language Learning']")
    public WebElement lang_learn_chkbox;

    @FindBy(xpath = "//*/div/button/span[text()='View']")
    public WebElement viewbtn;

    @FindBy(xpath = "//div[@class='cds-9 css-0 cds-11 cds-grid-item']/a[@data-testid='home-logo']")
    public WebElement coursera;

    public FreeCoursesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    
    
    
    public HomePage extractdata() throws InterruptedException{
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));


		topic.click();
		lang_learn_chkbox.click();
		viewbtn.click();
	
		System.out.println("Language Learning Free Courses: ");
//		Extract data of courses
		List<WebElement> courses=driver.findElements(By.xpath("//*/div[@id='searchResults']/div/div/ul/li[starts-with(@class, 'cds-')]"));
		
//		System.out.println(courses);
		for(WebElement course: courses)
		{			
			String Cname=course.findElement(By.xpath(".//div[@class='cds-ProductCard-header']/div[2]/a/h3")).getText();
			String Crating=course.findElement(By.xpath(".//div[@class='cds-ProductCard-footer']/div[2]/div/span")).getText();
			String Creview=course.findElement(By.xpath(".//div[@class='cds-ProductCard-footer']/div[2]/div[2]")).getText();
			String Cduration=course.findElement(By.xpath(".//div[@class='cds-ProductCard-footer']/div[3]/p")).getText();
			
			System.out.println("Course Name: "+Cname);
			System.out.println("Course Rating: "+Crating);
			System.out.println("Course Reviews: "+Creview);
			System.out.println("Course Duration: "+Cduration);
			System.out.println();
			
		}
		
		coursera.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

}
