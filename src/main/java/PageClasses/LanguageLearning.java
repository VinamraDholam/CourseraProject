package PageClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LanguageLearning {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div/ul/li/a[text()='Free Courses']")
    public WebElement click_free_course;

    public LanguageLearning(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public FreeCoursesPage click_free_courses() {
        // Wait for page title
        wait.until(ExpectedConditions.titleContains("Language Learning"));

        // Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        // Click Free Courses
        wait.until(ExpectedConditions.elementToBeClickable(click_free_course));
        click_free_course.click();

        return new FreeCoursesPage(driver);
    }
}
