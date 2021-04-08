package com.actitime.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DriverScript {
	
	public static WebDriver driver;
	Properties  prop;

	public DriverScript(){
		try{
		File file = new File("./properties/config.properties");
		
		FileInputStream fis = new FileInputStream(file);
		 prop = new Properties();
		prop.load(fis);
		}
		catch(Exception e){
			System.out.println("Can't able to load the file" +e.getMessage());
		}
	}
	@Test
	public void initiConfiguration(){
		
		
	     String browser = prop.getProperty("browser");
	     if(browser.equalsIgnoreCase("chrome")){
	    	 System.setProperty("webdriver.chrome.driver", "./Browsers/chromedriver.exe");
	    	 driver = new ChromeDriver();
	     }else if(browser.equalsIgnoreCase("firefox")){
	    	 System.setProperty("webdriver.chrome.driver", "./Browsers/geckodriver.exe");
	    	 driver = new FirefoxDriver();
	     }else if(browser.equalsIgnoreCase("edge")){
	    	 System.setProperty("webdriver.chrome.driver", "./Browsers/msedgedriver.exe");
	    	 driver = new EdgeDriver();
	     }
	     else
	     {
	    	 System.out.println("Please provide the correct url ");
	     }
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("qaurl"));
		
		}
	
	public void closeApplication(){
		driver.close();
	}
}
