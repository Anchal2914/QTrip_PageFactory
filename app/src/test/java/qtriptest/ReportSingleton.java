package qtriptest;

import java.io.File;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ReportSingleton {

    private static ExtentTest test=null;
    private static ExtentReports getReport=null;


    private ReportSingleton()  {
        
	}
   
    public static ExtentReports getReport() throws MalformedURLException {
        if(getReport==null){
            // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            // String timestampString = String.valueOf(timestamp.getTime());
            getReport = new ExtentReports(System.getProperty("user.dir")+"/QTripExtentReport.html");
            getReport.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));
        }
        return getReport;
    }

    public static ExtentTest getTestInstance() {
        test = getReport.startTest("QTripTest");
    return test;
    }

    public static ExtentReports endTest() {
        getReport.endTest(test);
        getReport.flush();
        return getReport;
    }

}




