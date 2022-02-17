package com.liverpoolnavigation.pages;

import com.liverpoolnavigation.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BaseClass {

    //Initializing Page Objects
    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    //Elements Repository
    @FindBy (id = "mainSearchbar")
     WebElement searchField;

    @FindBy (css = "button.input-group-text")
     WebElement searchButton;


    //Action Methods
    public void searchItem(String item){
        searchField.sendKeys(item);
        searchButton.click();
    }

}
