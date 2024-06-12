import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class Ex7 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\wikipedia_android.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @After
    public void rotation() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
    }



    @Test
    public void testAssertElementPresent() {

        skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        assertElementPresent(By.xpath("//*[@text='Java (programming language)']"), 15);

    }

    @Test
    public void testSaveAndCompareTwoArticles() {

        skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article title", 10);

        waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "Cannot find button to save article", 5);

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot find 'Add to list' button", 5);

        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder,
                "Cannot put text into articles folder input", 5);

        waitForElementAndClick(By.xpath("//*[@text='OK']"), "Cannot press OK button", 5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article", 5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='High-level programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(By.xpath("//*[@text='JavaScript']"),
                "Cannot find article title", 10);

        waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "Cannot find button to save article", 5);

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot find 'Add to list' button", 5);

        waitForElementAndClick(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Cannot find saved list", 5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article", 5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close search result", 5);

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find navigation button to 'Saved'", 5);

        waitForElementAndClick(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Cannot find saved list", 5);

        swipeElementToLeft(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find saved article");

        String title_after_delete = waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='JavaScript']"),
                "text",
                "Cannot find article title",
                10);

        waitForElementAndClick(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='JavaScript']"),
                "Cannot find article title", 10);

        String bodyTitle_after_delete = waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='JavaScript']"),
                "text",
                "Cannot find article title",
                10);

        Assert.assertEquals("Titles do not match", title_after_delete, bodyTitle_after_delete);

    }




    //methods

    public void assertElementPresent(By by, long timeout) {
        WebElement element = driver.findElement(by);
        Boolean title = element.isDisplayed();
        if (title == false) {
            String default_message = "A title is not present";
            throw new AssertionError(default_message);
        }

    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeout) {
        WebElement element = waitForElementPresent(by, error_message, timeout);
        return element.getAttribute(attribute);
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(left_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .moveTo(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .release()
                .perform();
    }

    public WebElement skipOnboarding(By by, String error_message, long timeoutInSeconds) {
        WebElement elementSkip = waitForElementPresent(by, error_message, timeoutInSeconds);
        elementSkip.click();
        return elementSkip;
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


}
