package Utils;

import Utils.ExtentReporter;
import com.aventstack.extentreports.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Date;

public class Listeners implements ITestListener {
    ExtentReports extentReports= ExtentReporter.generateReports();
    ExtentTest extentTest;
    static ThreadLocal<ExtentTest> text=new ThreadLocal<ExtentTest>();
    public void onTestStart(ITestResult iTestResult) {
        text.set(extentReports.createTest(iTestResult.getMethod().getMethodName()));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        text.get().log(Status.PASS,iTestResult.getName());
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The name of the testcase failed is :"+iTestResult.getName());
        TakesScreenshot scrShot =((TakesScreenshot)WebDriverManager.getDriver());
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        try {


            if (!(new File("./ExecutionReport/Screenshots").exists()))
                new File("./ExecutionReport/Screenshots").mkdirs();
            String fileName="Screenshots/error" + new Date().getTime()+".png";
            File DestFile = new File("./ExecutionReport/"+fileName);

            try {
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            text.get().fail(iTestResult.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath("./"+fileName).build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }
}
