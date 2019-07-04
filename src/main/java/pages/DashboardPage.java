package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends ParentPage {
    public DashboardPage(WebDriver webDriver) {
        super(webDriver, "/dash");
    }
    public boolean isDashboardPresent(){
        try{
            return webDriver.findElement(By.xpath("//*[@id=\"dash_head\"]")).isEnabled();
        }catch (Exception e){
            return false;
        }
    }
}
