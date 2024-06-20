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

public class Ex6 {

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

        assertElementPresent(By.xpath("//*[@text='Java (programming language)']"));

    }


    //methods

    private void assertElementPresent(By by) {
        WebElement element = driver.findElement(by);
        Boolean title = element.isDisplayed();
        if (title == false) {
            String default_message = "A title is not present";
            throw new AssertionError(default_message);
        }

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
