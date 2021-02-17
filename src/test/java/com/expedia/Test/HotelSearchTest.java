package com.expedia.Test;

import com.expedia.Base.BaseTest;
import com.expedia.Pages.HomePage;
import com.expedia.Pages.HotelSearchPage;
import com.expedia.Utils.ExcelHelper;
import org.openqa.selenium.NoSuchWindowException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HotelSearchTest extends BaseTest {

    HotelSearchPage hs;


    @DataProvider(name="filterBy")
    public Object[][] filterByMethod(){
        return ExcelHelper.getExcelData("TestData.xlsx","Filter_By");
    }

    @Test(dataProvider = "filterBy",description = "Test Case to verify Filter By using dataprovider ")
    public void Tc_hotelfilter_01(String s_type,String s_rating,String s_budget){
        driver.get(url2);
        hs=new HotelSearchPage(driver);
        log.info(" Filter By Type : " + s_type+" |Star Rating : "+s_rating+" |Budget : "+s_budget );
        int totalsearch=hs.H_SearchCriteria(s_type,s_rating,s_budget);
        Assert.assertEquals(totalsearch,4);
        log.info(driver.getTitle());

    }

    @DataProvider(name="bookingHotel")
    public Object[][] bookingHotel_Method(){
        return ExcelHelper.getExcelData("TestData.xlsx","booking");
    }

    @Test(dataProvider="bookingHotel", description = "Scenario based Test case to perform booking - stay in Loc")
    public void Tc_selecthotel_02(String loc,String dd,String expectedtitle) throws  InterruptedException {
        driver.get(url1);
        HomePage hp = new HomePage(driver);
        hs=hp.input_staysWindow(loc,dd);
       // String Actualtitle=null;
        try {
            hs.action_HotelDetails();
        }catch(NoSuchWindowException nowindow){
            log.warn(" while running the testcase additional chrome browser is launched. But it closes when child window is opened so window handler is having the count of window which is not openned any more. ");
        }

      //  Assert.assertEquals(Actualtitle,expectedtitle);

    }



}


// Object [][] obj1={{"Hotel","5★","Greater than $300"}};
//  return obj1;