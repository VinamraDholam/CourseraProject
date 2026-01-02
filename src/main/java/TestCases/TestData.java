package TestCases;

import com.aventstack.extentreports.Status;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import BaseClasses.BasePageClass;
import BaseClasses.HomePage;
import PageClasses.FreeCoursesPage;
import PageClasses.GovernmentPage;
import PageClasses.LanguageLearning;
import PageClasses.WebDevelopmentPage;

public class TestData extends BasePageClass {

    @Test
    public void runCompleteFlow() throws  IOException, InterruptedException {

        logger = report.createTest("Coursera Project Test");

        logger.log(Status.INFO, "Opening Browser");
        invokeBrowser("Chrome");

        logger.log(Status.INFO, "Opening Coursera");
        HomePage home = OpenApplication();

        logger.log(Status.INFO, "Navigating to Search Results");
        WebDevelopmentPage web = home.searchCourse();
        
        logger.log(Status.INFO, "Applying Filters & Extracting First 2 Courses");
        web.webdevelopmentcourses();  

        logger.log(Status.INFO, "Opening Language Learning");
        LanguageLearning langPage = web.click_language_learning();

        logger.log(Status.INFO, "Opening Free Courses");
        FreeCoursesPage freePage = langPage.click_free_courses();

        logger.log(Status.INFO, "Extracting course details");
        freePage.extractdata();

        logger.log(Status.INFO, "Navigating to Government Page");
        GovernmentPage gov = home.goToGovernmentPage();

        logger.log(Status.INFO, "Scrolling to Government Form");
        gov.scrollToForm();

        logger.log(Status.INFO, "Filling Form Details");

        gov.enterFirstName("Vinamra");
        gov.enterLastName("Dholam");
        gov.enterWorkEmail("vinamradholam@gmail.com");  
        gov.enterPhoneNumber("9876543210");
        gov.selectOrganizationType("Government");
        gov.enterJobTitle("Tester");
        gov.enterOrganizationName("Infosys");
        gov.selectOrganizationSize("1-500");
        gov.selectNeed("Other");
        gov.selectExpectedLearners("26-100");
        gov.enterDescribeNeed("Need training for new employees");
        gov.selectCountry("India");
        

        logger.log(Status.FAIL, "Test Case Pass");
        gov.submitFormAndCaptureStateError();    
	}

    

    @AfterMethod
    public void flushReport() {
        report.flush();
    }
}
