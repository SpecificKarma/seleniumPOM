package amazon.pages;

import amazon.objects.Log;
import amazon.resources.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class BuyHeadphonesPage {
    private WebDriver driver;
    private JavascriptExecutor jquery;
    private String defaultTab;
    private List<WebElement> elements;
    private Set<String> tabs;


    public BuyHeadphonesPage(WebDriver driver) {
        this.driver = driver;
        jquery = (JavascriptExecutor) driver;
    }

    public void goTo(String URL) {
        try {
            driver.get(URL);
            Log.printLn("Redirected on " + Data.AMAZON_HOME);
        } catch (Exception e) {
            Log.printLn("Fail redirect to " + Data.AMAZON_HOME);
        }
    }

    public void searchBarClick() {
        try {
            driver.findElement(By.id("twotabsearchtextbox")).click();
            Log.printLn("Clicked on Search bar");
        } catch (Exception e) {
            Log.printLn("Fail click on search bar");
        }
    }

    public void searchBarType(String text) {
        try {
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys(text);
            Log.printLn(text + " typed");
        } catch (Exception e) {
            Log.printLn("Fail to type in search Bar");
        }
    }

    public void searchIconClick() {
        try {
            driver.findElement(By.xpath("//input[@value='Go']")).click();
            Log.printLn("Clicked on Magnifier icon");
        } catch (Exception e) {
            Log.printLn("Fail to click magnifier icon");
        }
    }

    public void collectLinksBestSellers() {
        try {
            elements = driver.findElements(By.xpath("//span[text()='Best Seller']" +
                    "/ancestor::div[@class='a-section a-spacing-medium']//a[@class='a-link-normal a-text-normal']"));
            Log.printLn("Best Seller links collected - " + elements.size());
        } catch (Exception e) {
            Log.printLn("Fail to collect links");
        }

    }

    public void openLinksInNewTabs() {
        setDefaultTab();
        try {
            for (WebElement e : elements) {
                jquery.executeScript("window.open('" + e.getAttribute("href") + "')");
                Log.printLn("New tab open - \"" + driver.getTitle() + "\"");
            }
        } catch (Exception e) {
            Log.printLn("Fail to open new tab");
        }
    }

    public void addItemsToCart() {
        tabs = driver.getWindowHandles();
        for (String t : tabs) {
            if (!t.equals(defaultTab)) {
                driver.switchTo().window(t);

                try {
                    driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
                    Log.printLn("Clicked on \"Add to Cart\" button");
                } catch (Exception e) {
                    Log.printLn("Fail to click on \"Add to Cart\" button");
                }

                try {
                    if (driver.findElement(By.xpath("//div[@class='a-popover-wrapper']")).isDisplayed()) {
                        driver.findElement(By.xpath("//span[@id='siNoCoverage']")).click();
                        driver.close();
                        continue;
                    }
                    Log.printLn("Clicked on \"No\" button in popup dialog");
                } catch (Exception e) {
                    Log.printLn("Popup dialog is not present");
                }

                try {
                    if (driver.findElement(By.xpath("//div[@class='a-section attach-desktop-sideSheet']")).isDisplayed()) {
                        driver.findElement(By.xpath("//span[@id='attachSiNoCoverage']")).click();
                        driver.close();
                        continue;
                    }
                    Log.printLn("Clicked on \"No\" button in side dialog");
                } catch (Exception e) {
                    Log.printLn("Side dialog is not present");
                }
                driver.close();
            }
        }
    }

    public void validateNoItemsInCart() {
        try {
            driver.switchTo().window(defaultTab).navigate().refresh();
            Log.printLn("Refresh page");
        } catch (Exception e) {
            Log.printLn("Unable refresh page");
        }

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='nav-cart-count']")).getText(),
                String.valueOf(elements.size() - 1));
    }

    public void setDefaultTab() {
        defaultTab = driver.getWindowHandle();
    }

    public String getDefaultTab() {
        return defaultTab;
    }
}
