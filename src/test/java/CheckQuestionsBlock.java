import PageObjects.MainPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckQuestionsBlock {
    public WebDriver driver;
    public final String questionHeader, answer;

    public CheckQuestionsBlock(String questionHeader, String answer) {
        this.questionHeader = questionHeader;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getFaqs() {
        return new Object[][]{
                {"������� ��� �����? � ��� ��������?", "����� � 400 ������. ������ ������� � ��������� ��� ������."},
                {"���� ����� ��������� ���������! ��� �����?", "���� ��� � ��� ���: ���� ����� � ���� �������. ���� ������ ���������� � ��������, ������ ������ ������� ��������� ������� � ���� �� ������."},
                {"��� �������������� ����� ������?", "��������, �� ���������� ����� �� 8 ���. �� �������� ������� 8 ��� � ������� ���. ������ ������� ������ ���������� � �������, ����� �� �������� ����� �������. ���� �� �������� ������� 8 ��� � 20:30, �������� ������ ���������� 9 ��� � 20:30."},
                {"����� �� �������� ������� ����� �� �������?", "������ ������� � ����������� ���. �� ����� ������ �����������."},
                {"����� �� �������� ����� ��� ������� ������� ������?", "���� ��� ���! �� ���� ���-�� ������� � ������ ����� ��������� � ��������� �� ��������� ������ 1010."},
                {"�� ��������� ������� ������ � ���������?", "������� ��������� � ��� � ������ ��������. ����� ������� �� ������ ����� � ���� ���� ������ �������� ��� ��������� � �� ���. ������� �� �����������."},
                {"����� �� �������� �����?", "��, ���� ������� �� ��������. ������ �� �����, �������������� ������� ���� �� ��������. ��� �� ����."},
                {"� ���� �� ������, ��������?", "��, �����������. ���� ���������! � ������, � ���������� �������."},
        };
    }

    @Test
    public void checkFaqList() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MainPage mp = new MainPage(driver);
        mp.openPage();
        List<WebElement> questionsList = mp.getQuestionsListElements();
        List<WebElement> answersList = mp.getAnswersListElements();
        for(int i=0; i < questionsList.size(); i++){
            WebElement el = questionsList.get(i);
            if(el.getText().equals(questionHeader)){
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", el);
                el.click();
                WebElement targetElement = answersList.get(i);
                new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(targetElement));
                assertEquals("����� ������ �� ������ �" +(i+1)+ " �� ��������� � ���������", answer, targetElement.getText());
                break;
            }
        }
        driver.quit();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
