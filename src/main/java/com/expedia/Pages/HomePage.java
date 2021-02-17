package com.expedia.Pages;

import com.expedia.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage{


    @FindBy(how= How.CSS,css = "#location-field-destination")
    WebElement ele_locationStay;

    @FindBy(how=How.ID,id="d1-btn")
    WebElement ele_checkinDate;

    @FindBy(how=How.ID,id="d2-btn")
    WebElement ele_checkoutDate;

    @FindBy(how=How.XPATH,xpath = "//div[@class='uitk-new-date-picker-month']/h2")
    WebElement ele_curMonthHeader;

    @FindBy(how=How.XPATH,xpath = "//div[@class='uitk-calendar']//button[2]")
    WebElement ele_nextButton;

    @FindBy(how=How.XPATH,xpath="//button[@data-stid='apply-date-picker']/span[contains(text(),'Done')]")
    WebElement ele_done;

    @FindBy(how=How.XPATH,xpath = "//button[@data-testid='submit-button']")
    WebElement ele_submit;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    public HotelSearchPage input_staysWindow(String location, String checkin) throws InterruptedException {

        driver.findElement(By.xpath("//button[@data-stid='location-field-destination-menu-trigger']")).click();

        ele_locationStay.sendKeys(location);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//ul[@data-stid='location-field-destination-results']/li/button//strong[contains(text(),'"+location+"')]")).click();

        String checkin_dd;
        String checkin_mm;
        String checkin_yyyy;
        String dateArray[] = checkin.split("/");
        checkin_mm = dateArray[0];
        checkin_dd = dateArray[1];
        checkin_yyyy = dateArray[2];

        ele_checkinDate.click();

        Actions actions= new Actions(driver);

        while (true) {
            String curmonth = ele_curMonthHeader.getText();
            if (curmonth.equals(checkin_mm + " " + checkin_yyyy)) {
                break;
            } else {
                //ele_nextButton.click();
                actions.moveToElement(ele_nextButton).click().build().perform();
            }
        }

        List<WebElement> all_date = driver.findElements(By.xpath("//div[@class='uitk-calendar']//div[1]//table[1]//tbody[1]//tr//td//button[1]"));

        for (WebElement ele : all_date) {
            String fromDate = ele.getAttribute("data-day");
            if (fromDate.equals(checkin_dd)) {
                actions.moveToElement(ele).click().build().perform();
                //ele.click();
                break;
            }

        }

        ele_done.click();
        Thread.sleep(2000);
        ele_submit.click();

        return new HotelSearchPage(driver);
       // return PageFactory.initElements(driver,HotelSearchPage.class);

    }

    public void handletempchildwindow() {


       String mainwindow = driver.getWindowHandle();
        Set<String> set = driver.getWindowHandles();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String child = it.next();
            if (!mainwindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                driver.close();
            }
        }
    }


}

