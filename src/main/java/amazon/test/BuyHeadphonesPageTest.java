package amazon.test;

import amazon.resources.Data;
import org.testng.annotations.Test;


public class BuyHeadphonesPageTest extends BaseTest {

    @Test(priority = 0)
    public void BuyHeadphonesTest() {
        pages.buyHeadphones.goTo(Data.AMAZON_HOME);
        pages.buyHeadphones.searchBarClick();
        pages.buyHeadphones.searchBarType(Data.HEADPHONES);
        pages.buyHeadphones.searchIconClick();
        pages.buyHeadphones.collectLinksBestSellers();
        pages.buyHeadphones.openLinksInNewTabs();
        pages.buyHeadphones.addItemsToCart();
        pages.buyHeadphones.validateNoItemsInCart();
    }
}