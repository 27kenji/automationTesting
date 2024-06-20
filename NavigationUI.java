package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
    CLOSE_SEARCH_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    SAVED_ON_MENU = "//android.widget.FrameLayout[@content-desc='Saved']";



    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }


    public void closeSearchResults() {
            this.waitForElementAndClick(By.xpath(CLOSE_SEARCH_BUTTON),
                    "Cannot close search result",5);
}
    public void clickSavedOnMenu() {
        this.waitForElementAndClick(By.xpath(SAVED_ON_MENU),
                "Cannot find navigation button to 'Saved'", 5);
    }
}
