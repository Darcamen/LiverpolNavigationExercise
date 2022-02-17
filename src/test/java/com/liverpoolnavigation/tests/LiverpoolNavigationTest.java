package com.liverpoolnavigation.tests;

import com.liverpoolnavigation.base.BaseClass;
import com.liverpoolnavigation.pages.HomePage;
import com.liverpoolnavigation.pages.ResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LiverpoolNavigationTest extends BaseClass {

    HomePage homePage;
    ResultsPage resultPage;

    public LiverpoolNavigationTest(){ super(); }

    @BeforeMethod
    public void initBrowser() throws InterruptedException {
        browserSetup();
        homePage = new HomePage();
        resultPage = new ResultsPage();
    }

    @Test(priority = 1)
    public void validItemSearchTest(){
        homePage.searchItem(propFile.getProperty("item1"));
        Assert.assertTrue(resultPage.resultsValidation());
        System.out.println("Search of " + propFile.getProperty("item1") + " performed successfully!");
    }

    @Test(priority = 2)
    public void invalidItemSearhTest(){
        homePage.searchItem(propFile.getProperty("wrongItem"));
        String errorMsg = resultPage.errorValidation();
        Assert.assertEquals(errorMsg,"Busca de nuevo");
        System.out.println("Invalid search of " + propFile.getProperty("wrongItem") + " performed successfully!");
    }

    @Test(priority = 3)
    public void customizedSearchTest(){
        homePage.searchItem(propFile.getProperty("item2"));
        resultPage.customizedSearch();
        Assert.assertTrue(resultPage.filterBoxValidation());
        String filteredSearch = resultPage.filterResults();
        Assert.assertEquals(filteredSearch,"Your filters are: "+propFile.getProperty("filter1")+", "+ propFile.getProperty("filter2")+ " and "+ propFile.getProperty("filter3"));
        System.out.println(filteredSearch);
    }

    @AfterMethod
    public void tearDown(){
       driver.quit();
    }
}
