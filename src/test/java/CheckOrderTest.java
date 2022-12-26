import PageObjects.MainPage;
import PageObjects.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class CheckOrderTest {

    private final boolean useDownButton;
    private final String name, surname, address, metro, phone, date, comment;
    private final String color;
    private final int days;

    public WebDriver driver;

    public CheckOrderTest(boolean useDownButton, String name, String surname, String address, String metro, String phone, String date, int days, String color, String comment) {
        this.useDownButton = useDownButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.color = color;
        this.days = days;
    }

    @Parameterized.Parameters
    public static Object[][] getFaqs() {
        return new Object[][]{
                {true, "�������", "�����", "�� ��� �����", "����������� ����", "89111234567", "31.12.2022", 5, "������ ������", "��� ������������"},
                {true, "Chepik", "Arseny", "To my work", "�������", "89111234567", "25.12.2022", 10, "����� �������������", ""},
        };
    }

    @Test
    public void orderTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MainPage mp = new MainPage(driver);
        mp.openPage();
        if (useDownButton) {
            mp.clickDownOrderButton();
        } else {
            mp.clickUpperOrderButton();
        }
        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterFirstPageAndProceed(name, surname, address, metro, phone);
        orderPage.enterSecondPageAndPlaceOrder(date, days, color, comment);
        Assert.assertTrue("����� ��������", orderPage.orderPlaced());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
