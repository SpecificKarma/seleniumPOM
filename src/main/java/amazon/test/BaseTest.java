package amazon.test;

import amazon.objects.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver = new ChromeDriver();
    public PageObjects pages = new PageObjects(driver);

    @BeforeTest
    public void setUp() throws AWTException {
        new Robot().mouseMove(0,0);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
