package com.expedia.Base;

import com.expedia.Utils.ConfigUtil;
import com.expedia.Utils.ExcelHelper;
import com.expedia.Utils.WebDriverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected static Logger log = LogManager.getLogger(ExcelHelper.class);
    protected static String browser=null;
    protected static String url1=null;
    protected static String url2=null;
// declared the Url i respective test class because passing two different url




    @BeforeSuite
    public void beforeSuite(){
        ConfigUtil configUtil=new ConfigUtil(System.getProperty("user.dir")+"/src/main/resources/config.properties");
        browser=configUtil.getProperty("browser");
        url1=configUtil.getProperty("HomePageUrl");
        url2=configUtil.getProperty("HotelSearchUrl");
    }



    @BeforeMethod
    public void setup(Method method){
        driver=WebDriverUtil.getDriver(browser);
       //WebDriverUtil_singleton singletonobj= WebDriverUtil_singleton.getInstance();
      //driver=singletonobj.getDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        log.info(method.getAnnotation(Test.class).description());
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()){
            log.error("Test Case  FAILED "+ result.getName());
            log.error("Test Case  FAILED "+ result.getThrowable());
        }else if(ITestResult.SUCCESS==result.getStatus()){
            log.info("Test Case  PASSED "+ result.getName());
        }else if(ITestResult.SKIP==result.getStatus()){
            log.info("Test Case  SKIPPED "+ result.getName());
        }
        driver.quit();
    }
}
