package com.expedia.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverUtil {

    // Factory Design Pattern
    public static WebDriver getDriver(String browserName){
        WebDriver driver=null;
        switch(browserName){
            case "chrome":
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
                driver=new ChromeDriver();
                break;
            case "firefox":
                //  System.setProperty("webdriver.gecko.driver",\\geckodriver.exe);
                //  driver=new FirefoxDriver();
                break;

            default :
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
                driver=new ChromeDriver();
                break;
        }
        return driver;

    }
}
