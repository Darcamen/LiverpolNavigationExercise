package com.liverpoolnavigation.base;

import com.liverpoolnavigation.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public static Properties propFile = new Properties();

    public BaseClass(){
        try{
            FileInputStream inputFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/liverpoolnavigation/config/config.properties");
            propFile.load(inputFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void browserSetup() {
        String browser = propFile.getProperty("browser");

        //Setting up Multi-Browser Driver
        if (browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();

            //Handling Geolocation alert
            // Setup Settings Layers (Top to Bottom)
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            Map<String, Object> profile = new HashMap<String, Object>();
            Map<String, Object> contentSettings = new HashMap<String, Object>();

            // Specify the ChromeOption we need to set
            contentSettings.put("geolocation",2);
            profile.put("managed_default_content_settings", contentSettings);
            prefs.put("profile", profile);
            options.setExperimentalOption("prefs", prefs);

            //Declaring capability for ChromeOptions
            driver = new ChromeDriver(options);

        }else if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            // Specify the ChromeOption we need to set
            options.addPreference("permissions.default.geo",2);

            // Specify the ChromeOption we need to set
            driver = new FirefoxDriver(options);
        }
        driver.get(propFile.getProperty("url"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICIT_WAIT));

    }

    public static void waitFor(WebElement element){
         new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(element));
    }



}
