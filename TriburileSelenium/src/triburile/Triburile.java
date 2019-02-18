package triburile;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Triburile {
    static Random rand = new Random();
    static int lemn, piatra, fier;
    
    public static void updateRes(WebDriver driver){
         Date date = new Date();
         lemn = Integer.parseInt(driver.findElement(By.className("smallPadding")). findElement(By.id("wood")).getText());
         fier = Integer.parseInt(driver.findElement(By.className("smallPadding")). findElement(By.id("iron")).getText());   
         piatra = Integer.parseInt(driver.findElement(By.className("smallPadding")). findElement(By.id("stone")).getText());
         System.out.println(new Timestamp(date.getTime())+" | Resurse -> Lemn: "+lemn+", Piatra: "+piatra+", Fier: "+fier);
    }
    public static int check(WebDriver driver,String clad){   
        int rLemn,rPt,rFier;
     try{
        WebElement cost = driver.findElement(By.id("buildings")).findElement(By.id("main_buildrow_"+clad));
        rLemn = Integer.parseInt(cost.findElement(By.className("cost_wood")).getText());
        rPt = Integer.parseInt(cost.findElement(By.className("cost_stone")).getText());
        rFier = Integer.parseInt(cost.findElement(By.className("cost_iron")).getText());
        if(rLemn < lemn && rPt < piatra && rFier < fier){
            try{
                if(driver.findElement(By.id("build_queue")).findElements(By.className("lit-item")).size() == 2){    
                      System.out.println("Nu!");
                      return 0;
                }
            }
            catch(NoSuchElementException da){ }
            return 1;
        }
     }catch(NoSuchElementException | StaleElementReferenceException d2){ 
            System.out.println("Exception la check.");
     }
            
      return 0;
    }
    public static void upgrade(WebDriver driver, String clad){
        try{
        driver.findElement(By.id("buildings")).
               findElement(By.id("main_buildrow_"+clad)).findElement(By.className("build_options")).
               findElement(By.className("btn-build")).click();
               
        String []lvl = driver.findElement(By.id("buildings")).findElement(By.id("main_buildrow_"+clad)).getText().split("\n");
        Date date = new Date();
        System.out.println(new Timestamp(date.getTime())+ " | Am dat comanda de upgrade a ["
                +clad+"] de la "+lvl[1].substring(0,7));
         } catch(ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException d2){ 
             System.out.println("Exception la upgrade.");
         }
    }
   public static void stop(int cat) throws InterruptedException{
        Thread.sleep(cat*1000 + rand.nextInt(2000));
   } 
   
    public static void main(String[] args) throws InterruptedException {
            
       System.setProperty("webdriver.chrome.driver", "D:/Downloads/Java/chromedriver.exe");
       WebDriver driver = new ChromeDriver();
       // --------------------- LOGIN ----------------------------
       driver.get("https://en101.tribalwars.net/");
       driver.findElement(By.id("user")).sendKeys("test6666");
       //driver.findElement(By.id("password")).sendKeys("");    
       //driver.findElement(By.className("btn-login")).click();
        int pass = 0;
        while(pass == 0)
        try{
            stop(1);
            driver.findElement(By.className("world_button_active")).click();
            pass = 1;
        }
        catch(NoSuchElementException da){ }
        
       // ---------------------------------- MENIU ----------------------------------
       //  driver.get("https://en101.tribalwars.net/game.php?village=14215&screen=overview");  
       stop(2);
       driver.get("https://en101.tribalwars.net/game.php?village=14215&screen=main");
       
       int rPiatra,rLemn,rFier;
       String []cladiri = {"wood","stone","iron","farm","storage"};
       stop(1);
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,150)");
       while(true){
            updateRes(driver); 
            
            
            for(String it : cladiri){
                if(check(driver, it) == 1){
                      upgrade(driver, it);
                }
            }
            stop(30);
       }         
    }
}
