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
    public void runCompleteFlow() throws IOException, InterruptedException {

        // Create a test in the Extent report with a name
        logger = report.createTest("Coursera Project Test");

        // Step 1: Open the browser
        logger.log(Status.INFO, "Opening Browser");
        invokeBrowser("Chrome");

        // Step 2: Open Coursera home page
        logger.log(Status.INFO, "Opening Coursera");
        HomePage home = OpenApplication();

        // Step 3: Navigate to Web Development search results
        logger.log(Status.INFO, "Navigating to Search Results");
        WebDevelopmentPage web = home.searchCourse();
        
        // Step 4: Apply filters and extract details of first 2 courses
        logger.log(Status.INFO, "Applying Filters & Extracting First 2 Courses");
        web.webdevelopmentcourses();  

        // Step 5: Navigate to Language Learning section
        logger.log(Status.INFO, "Opening Language Learning");
        LanguageLearning langPage = web.click_language_learning();

        // Step 6: Open Free Courses page
        logger.log(Status.INFO, "Opening Free Courses");
        FreeCoursesPage freePage = langPage.click_free_courses();

        // Step 7: Extract course details
        logger.log(Status.INFO, "Extracting course details");
        freePage.extractdata();

        // Step 8: Navigate to Government Page
        logger.log(Status.INFO, "Navigating to Government Page");
        GovernmentPage gov = home.goToGovernmentPage();

        // Step 9: Scroll to the form section on Government Page
        logger.log(Status.INFO, "Scrolling to Government Form");
        gov.scrollToForm();

        // Step 10: Fill in the form details
        logger.log(Status.INFO, "Filling Form Details");

        gov.enterFirstName("Vinamra");           // Enter first name
        gov.enterLastName("Dholam");             // Enter last name
        gov.enterWorkEmail("vinamradholam@gmail.com");  // Enter email
        gov.enterPhoneNumber("9876543210");      // Enter phone number
        gov.selectOrganizationType("Government");// Select organization type
        gov.enterJobTitle("Tester");             // Enter job title
        gov.enterOrganizationName("Infosys");    // Enter organization name
        gov.selectOrganizationSize("1-500");     // Select organization size
        gov.selectNeed("Other");                 // Select need
        gov.selectExpectedLearners("26-100");   // Select expected learners
        gov.enterDescribeNeed("Need training for new employees"); // Describe need
        gov.selectCountry("India");              // Select country
        
        // Step 11: Submit the form and capture error if any
        logger.log(Status.FAIL, "Test Case Pass"); // For demo, we mark fail to show report logging
        gov.submitFormAndCaptureStateError();      // Submit form
    }

    @AfterMethod
    public void flushReport() {
        // Flush the Extent report after each test
        report.flush();
    }
}
