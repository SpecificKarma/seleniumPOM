package amazon.objects;

import amazon.pages.BuyHeadphonesPage;
import amazon.resources.Data;
import org.openqa.selenium.WebDriver;

public class PageObjects {
    public Data data;
    public BuyHeadphonesPage buyHeadphones;

    public PageObjects(WebDriver driver) {
        data = new Data();
        buyHeadphones = new BuyHeadphonesPage(driver);
    }
}
