package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    private By upperOrderButton = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");
    private By downOrderButton  = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickUpperOrderButton(){
        driver.findElement(upperOrderButton).click();
    }

    public void clickDownOrderButton(){
        driver.findElement(downOrderButton).click();
    }

}
