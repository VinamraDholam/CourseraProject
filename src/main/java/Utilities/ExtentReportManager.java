package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {

    private static ExtentReports report;

    public static ExtentReports getReportInstance() {

        if (report == null) { // Singleton pattern to avoid multiple instances
            String reportName = "TestReport_" + DateUtils.getTimeStamp() + ".html";

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
                    System.getProperty("user.dir") + "\\test-output\\" + reportName);

            report = new ExtentReports();
            report.attachReporter(htmlReporter);

            report.setSystemInfo("OS", "Windows 11");
            report.setSystemInfo("Environment", "QA");
            report.setSystemInfo("Tester", "Vinamra");

            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Coursera Test Report");
            htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        }

        return report;
    }
}
