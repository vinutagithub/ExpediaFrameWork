package com.expedia.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverUtil_singleton {

    private static WebDriverUtil_singleton webDriverUtil_singleton = null ;
    private WebDriver driver;

    private  WebDriverUtil_singleton(){

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
        driver=new ChromeDriver();


    }

    public static WebDriverUtil_singleton getInstance(){
        if(webDriverUtil_singleton==null){
            webDriverUtil_singleton=new WebDriverUtil_singleton();
        }
        return webDriverUtil_singleton;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
