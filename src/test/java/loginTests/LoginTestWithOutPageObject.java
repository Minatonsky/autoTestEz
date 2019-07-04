package loginTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginTestWithOutPageObject {

    WebDriver webDriver;

    @Before
    public void setUp(){
        File file = new File("./src/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        webDriver = new ChromeDriver();
    }

    @Test
    public void validLogIn() throws InterruptedException {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.get("https://dev.ezlogz.com");

        webDriver.findElement(By.xpath(".//*[@class=\"log_in btn-login\"]")).click();
        webDriver.findElement(By.xpath(".//*[@id=\"formLogin\"]/div[1]/input")).sendKeys("rose@emailate.com");
        webDriver.findElement(By.xpath(".//*[@id=\"loginPass\"]")).sendKeys("testtest");
        webDriver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();
        Thread.sleep(10000);

        Assert.assertTrue("Not Login", isDashboardPresent());


    }
    @After
    public void tearDown(){
        webDriver.quit();
    }

    private boolean isDashboardPresent(){
        try{
            return webDriver.findElement(By.xpath("//*[@id=\"dash_head\"]")).isEnabled();
        }catch (Exception e){
            return false;
        }
    }

}
