package com.expedia.Base;

import com.expedia.Utils.ExcelHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected static Logger log = LogManager.getLogger(ExcelHelper.class);

    @FindBy(how=How.XPATH,xpath="//ul[@id='uitk-tabs-button-container']/li[1]/a[@role='tab']")
    WebElement ele_hotelstay;

    public BasePage(WebDriver driver){

        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    public String get_title(){

       String title=driver.getTitle();
       return title;

    }
}
