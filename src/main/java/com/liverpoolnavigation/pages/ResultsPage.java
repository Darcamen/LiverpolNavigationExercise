package com.liverpoolnavigation.pages;

import com.liverpoolnavigation.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage extends BaseClass {



    //Initializing Page Objects
    public ResultsPage(){
        PageFactory.initElements(driver,this);
    }

    //Elements Repository
    @FindBy(css = ".a-title__searchPLP.mt-5")
    WebElement itemNotFoundMsg;

    @FindBy(css = ".col-12.p-2.p-lg-1")
    WebElement searchResults;

    @FindBy(id = "dynamicFacets.genderatt-Hombre")
    WebElement genderCheckbox;

    @FindBy(id = "variants-normalizedColor-Negro")
    WebElement colorCheckbox;

    @FindBy(id = "brand-ADIDAS")
    WebElement brandCheckbox;

    @FindBy(xpath = "(//div[@class='plp-filters-container mt-3 mb-3'])[2]")
    WebElement filterBox;

    @FindBy(xpath = "(//div[contains(text(),'ADIDAS')])[2]")
    WebElement filter1;

    @FindBy(xpath = "(//div[contains(text(),'Hombre')])[2]")
    WebElement filter2;

    @FindBy(xpath = "(//div[contains(text(),'Negro')])[2]")
    WebElement filter3;

    //Action Methods
    public void customizedSearch(){
        genderCheckbox.click();
        colorCheckbox.click();
        brandCheckbox.click();
    }

    public boolean resultsValidation(){
        return searchResults.isDisplayed();
    }

    public String errorValidation(){
        return itemNotFoundMsg.getText();
    }

    public boolean filterBoxValidation(){return filterBox.isDisplayed();}

    public String filterResults(){
        waitFor(filter1);
        waitFor(filter2);
        waitFor(filter3);

       return "Your filters are: " +filter1.getText()+", "+filter2.getText()+ " and "+filter3.getText();
    }

}
