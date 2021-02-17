package com.expedia.Test;

import com.expedia.Base.BaseTest;
import com.expedia.Pages.HomePage;
import com.expedia.Pages.HotelSearchPage;
import com.expedia.Utils.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    HomePage hp;

   // TestCase to verify Page title of main site
    @Test
    public void title(){
        driver.get(url1);
        hp=new HomePage(driver);
        System.out.println(hp.get_title());
        Assert.assertEquals(hp.get_title(),"Expedia Travel: Search Hotels, Cheap Flights, Car Rentals & Vacations");
    }

    //DataProvider for where to day location, date , search Title
    @DataProvider(name="stayInfoData")
    public Object[][] stayData(){
        return ExcelHelper.getExcelData("TestData.xlsx","Sheet1");
    }

    // TestCase using DataProvider to search accommodation
    @Test(dataProvider = "stayInfoData", description = "Test Case to search stay based on location and check in date")
    public void Tc_stayloc_01(String loc,String cdate,String searchtitle) throws InterruptedException {
        log.info(" TestCase using DataProvider to search accommodation ");
        driver.get(url1);
        hp=new HomePage(driver);
//        if(popup_Alert()){
//            driver.switchTo().alert().dismiss();
//            driver.switchTo().defaultContent();
//        }
        HotelSearchPage hs=hp.input_staysWindow(loc,cdate);
        log.info("Test Case for  --------"+ searchtitle);
        Assert.assertEquals(hs.get_title(),searchtitle);
        log.info("Passed");
    }

    public Boolean popup_Alert(){
        try{
            driver.switchTo().alert();
            return true;
        }catch(Exception e){
            return false;
        }
    }



}
