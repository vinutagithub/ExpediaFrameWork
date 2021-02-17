package com.expedia.Pages;

import com.expedia.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HotelSearchPage extends BasePage {


    @FindBy(how=How.XPATH,xpath="//form[@action='/Hotel-Search']/fieldset[2]//div[@class='uitk-layout-grid-item']//input")
    List<WebElement> List_hotelSearch;

    @FindBy(how=How.XPATH,xpath="//form[@action='/Hotel-Search']/fieldset[3]//label[@class='uitk-button-toggle-label']/span[@class='uitk-button-toggle-content']")
    List<WebElement> List_hotelStars;

    @FindBy(how=How.XPATH,xpath = "//form[@action='/Hotel-Search']/fieldset[4]//div[@class='uitk-layout-grid-item']//label")
    List<WebElement> List_hotelBudget;

    @FindBy(how=How.ID,id="sort")
    WebElement ele_sort;

    @FindBy(how=How.XPATH,xpath = "//section//li[1]//div[@class='all-t-padding-one']//button[@data-stid='submit-hotel-reserve']")
    WebElement ele_reserve;


    public HotelSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    /*Action to select multiple filters from the Hotel Search Page.*/

    public Integer H_SearchCriteria(String type,String srating,String budget) {


        WebDriverWait wait= new WebDriverWait(driver,60);
        driver.navigate().refresh();
        driver.switchTo().defaultContent();

        for(WebElement ele : List_hotelSearch){
            String str1= ele.getAttribute("value");
            if(type.equalsIgnoreCase(str1)) {
                log.info("Search Criteria for type Popular Filter = " + str1);
                ele.click();
            }
        }


//        Filter For Stars ---
        for(WebElement ele: List_hotelStars){
            String str2=ele.getText();
            if(srating.equalsIgnoreCase(str2)){
                log.info("Search Criteria for Hotel Star rating : "+ str2);
                ele.click();
            }
        }
//        Filter for Budget of Hotel---
        for(WebElement ele: List_hotelBudget){
            String str3=ele.getText();
            if(budget.equalsIgnoreCase(str3)){
                log.info("Search Criteria for your Budget : "+ str3);
                ele.click();
            }
        }


        driver.navigate().refresh();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("playback-pill-grid-item-label")));


        List<WebElement> ls=driver.findElements(By.className("playback-pill-grid-item-label"));
        int val=ls.size();

        return val;

    }

    /*Action to perform - to view the selected hotel details to book reservation*/

    public void action_HotelDetails() throws InterruptedException {

        WebDriverWait wait= new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.elementToBeClickable(ele_sort));


        Select select = new Select(ele_sort);
        Actions actions = new Actions(driver);
        ele_sort.click();
        select.selectByValue("REVIEW");

        String mainWindow = driver.getWindowHandle();

        Thread.sleep(3000);


        driver.findElement(By.xpath("//section[@class='results']//li[1]")).click();



        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> it = s1.iterator();
        while (it.hasNext()) {

            String childWindow = it.next();

            if (!mainWindow.equalsIgnoreCase(childWindow)) {

                driver.switchTo().window(childWindow);
               // driver.findElement(By.xpath("//button[@data-stid='sticky-button']")).click();
                wait.until(ExpectedConditions.elementToBeClickable(ele_reserve));
                ele_reserve.click();

            }

        }

       // Thread.sleep(3000);

       // driver.switchTo().alert();

       // String title=driver.findElement(By.className("uitk-toolbar-title")).getText();
        //System.out.println(title);



    }



}
