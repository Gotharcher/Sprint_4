import PageObjects.MainPage;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CheckOrderTest {

    public WebDriver driver;

    @Test
    public void chromeTest() {
        driver = new ChromeDriver();
        MainPage mp = new MainPage(driver);
        mp.openPage();
        mp.clickUpperOrderButton();
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void firefoxTest() {
        driver = new FirefoxDriver();
        MainPage mp = new MainPage(driver);
        mp.openPage();
        mp.clickUpperOrderButton();
        System.out.println(driver.getCurrentUrl());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
