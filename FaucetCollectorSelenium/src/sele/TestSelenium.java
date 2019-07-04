package sele;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TestSelenium {
    static Random rand = new Random();
    static WebDriver driver = null;
    
    public static void stop(int cat) throws InterruptedException{
        Thread.sleep(cat*1000 + rand.nextInt(2000));
    } 

    public static void main(String[] args) throws InterruptedException 
    {  	 
    	 int i = 0,j;  
    	 
    	 Actions actions = null;
    	 WebElement plm;
 	    Runtime.getRuntime().addShutdownHook(new Thread()
 	    {
 	        @Override
 	        public void run()
 	        {
 	        	System.out.println("-------INCHIS-------");
 	        	try{
 	        		driver.close();	  
 	        		driver.quit();
 	            }
 	        	catch(WebDriverException exp){ }
 	        }
 	    });
/*	 ChromeOptions options = new ChromeOptions();
   	 options.addArguments("--window-size=1920,1080");
   	 options.addArguments("--headless");
   	 options.addArguments("--disable-gpu");
   	 options.addArguments("--disable-extensions");
   	 options.setExperimentalOption("useAutomationExtension", false);
   	 options.addArguments("--proxy-server='direct://'");
   	 options.addArguments("--proxy-bypass-list=*");
   	 options.addArguments("--start-maximized");*/
 	    
    	 try{	     	
	    	 while(i == 0)
	    	 {
		    	try{
		    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");
		    		driver = new ChromeDriver();//options);
		    		actions = new Actions(driver);
		    		i = 1;
		    		System.out.println("FOUND CHROME");
		    	} 
		    	catch(Exception ex){
		    		System.out.println("CHROMEDRIVER NOT FOUND");
					stop(15);				
		    	}
	    	}      
	             
	       Console console = System.console();
	       char[] user = console.readPassword("User: ");
	       char[] pw = console.readPassword("Password: ");
	       
	       driver.get("https://h2ox.io/");
	       stop(10);
	       driver.findElements(By.cssSelector(".smnu span")).get(1).click();
	       stop(1);
	       
	       driver.findElement(By.id("login")).sendKeys(new String(user));
	       driver.findElement(By.id("loginpw")).sendKeys(new String(pw));            
	       stop(1);       
	       driver.findElement(By.id("loginsubmit")).click(); 
	       
	       user = pw = new char[3]; 
	       List<WebElement> lista;
	       
	       while(true)
	       {
	    	   driver.get("https://h2ox.io/faucets");
		       lista = driver.findElements(By.className("faucetBox"));    
		       System.out.println("START!!");
		       
		       for(i = 1;i<lista.size();i++)
		       {
		    	   j = 0;
		    	   try{
		    		   plm = lista.get(i).findElement(By.cssSelector(".one.claimed"));
		    		   System.out.println(i+" CLAIMED ALREADY...");
		    		   continue;
		    	   }
		    	   catch(ElementNotVisibleException | NoSuchElementException exp){ }
		    	   
		    	   try{
		    		   plm = lista.get(i).findElement(By.cssSelector(".one"));
		    		   if(plm.findElements(By.cssSelector(".heading")).get(0).getText().length() == 0)
		    		   {
		    			   System.out.println(i+" ESTE AD...");
		    			   continue;
		    		   }
		    	   }
		    	   catch(Exception exp)
		    	   { 
		    		   System.out.println(exp.toString());
		    		   continue; 
		    	   }
		    	   
		    	   System.out.println("DISPONIBIL: "+i);
	    		   actions.moveToElement(plm).click().perform();
	    		   	    		   
		    	   
		    	   while(j++ != 6)
		    	   {
		    		   stop(1);
			           try{	             
			        	   System.out.println("Incercare "+j);
			        	   if(driver.findElement(By.id("claimbtn")).getText().equals("CLAIM"))
			        	   {
			        		   actions.moveToElement(driver.findElement(By.id("claimbtn"))).click().perform();
			        		   j = 6;
			        		   System.out.println("OK CLAIM!!");
			        		   stop(1);  
			        	   }	        	  	                          
			           } 
			           catch(ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException d2){}
		    	   } 
		    	   driver.get("https://h2ox.io/faucets");
		    	   stop(2);	    	   
		    	   lista = driver.findElements(By.className("faucetBox"));  
		       }
		       System.out.println("PAUZA 10 MINUTE!!");
		       stop(600);
	       }
		}
	    catch(WebDriverException op){
	    	System.out.println("CHROME A FOST INCHIS");
	    }
    }
}
