package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;

public class ExtentReporter {
    static ExtentReports  extentReports;
    public static ExtentReports generateReports(){
        if (!(new File("./ExecutionReport").exists()))
            new File("./ExecutionReport").mkdirs();
        ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter("./ExecutionReport/test.html");
        extentReports=new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        return extentReports;
    }
}
