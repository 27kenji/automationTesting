import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import java.time.Duration;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }




    //@Test
    //@BeforeEach
    //public void firstTest() {
    //WebElement elementSkip = driver.findElementByXPath("//*[contains(@text, 'Skip')]");
    //elementSkip.click();
    //}


    @Test
    public void testSearch() {


       /*WebElement elementSkip1 = driver.findElementByXPath("//*[contains(@text, 'Continue')]");
       elementSkip1.click();

       WebElement elementSkip2 = driver.findElementByXPath("//*[contains(@text, 'Continue')]");
       elementSkip2.click();

       WebElement elementSkip3 = driver.findElementByXPath("//*[contains(@text, 'Continue')]");
       elementSkip3.click();

       WebElement elementSkip4 = driver.findElementById("org.wikipedia:id/fragment_onboarding_done_button");
       elementSkip4.click();

       /*WebElement elementTap = waitForElement("//*[contains(@text, 'Search Wikipedia')]", "Cannot find Search Wikipedia input");
       elementTap.click();

       WebElement elementEnter = driver.findElementByXPath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']");
       elementEnter.sendKeys("Java");*/

        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }


    @Test
    public void testCancelSearch() {

        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }


    @Test
    public void testCompareArticleTitle() {

        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");



        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );


        WebElement title_element = MainPageObject.waitForElementPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article title", 10);


        String article_title = title_element.getAttribute("text");


        Assert.assertEquals("Unexpected title", "Java (programming language)", article_title);
    }


    @Test
    public void testClearSearch() {

        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5);

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_container"),
                "Page is not clear",
                5
        );

    }


    @Test
    public void testSwipeUpArticle() {

        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "article without links",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='NTFS links']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );


        MainPageObject.waitForElementPresent(By.xpath("//*[@text='Computer file system']"),
                "Cannot find article title", 10);

        MainPageObject.testSwipeUpArticle(2000);
        MainPageObject.testSwipeUpArticle(2000);
        MainPageObject.testSwipeUpArticle(2000);
        MainPageObject.testSwipeUpArticle(2000);
        MainPageObject.testSwipeUpArticle(2000);

    }



    @Test
    public void testSwipeUpToFindElement() {

        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );


        MainPageObject.waitForElementPresent(By.xpath("//*[@text='Automation for Apps']"),
                "Cannot find article title", 10);

        MainPageObject.testSwipeUpToFindElement(By.xpath("//*[@text='View article in browser']"), "Cannot find the end of the article", 5);

    }

    @Test
    public void testSaveFirstArticleToMyList() {
        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );


        MainPageObject.waitForElementPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article title", 10);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "Cannot find button to save article", 5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']"), "Cannot find 'Add to list' button", 5);

        String name_of_folder = "Learning programming";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder,
                "Cannot put text into articles folder input", 5);

        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"), "Cannot press OK button", 5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article", 5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close search result", 5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find navigation button to 'Saved'", 5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Cannot find saved list", 5);

        MainPageObject.swipeElementToLeft(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find saved article");

        MainPageObject.waitForElementNotPresent(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot delete saved article", 5);


    }

    @Test
    public void testSwipeUpOnboarding() {
        MainPageObject.swipeOnboarding(10);
    }


    @Test
    public void testAmountOfNotEmptySearch() {
        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Григорий Перельман";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        MainPageObject.waitForElementPresent(By.xpath(search_result_locator), "Cannot find anything by the request " + search_line, 15);

        int amount_of_search_results = MainPageObject.getAmountOfElements(By.xpath(search_result_locator));
         Assert.assertTrue("Found too few results", amount_of_search_results > 0);

    }



    @Test
    public void testAmountOfEmptySearch() {
        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "hgfhfdffh";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        MainPageObject.waitForElementPresent(By.xpath(empty_result_label), "Cannot find empty result label by the request " + search_line, 15);

        MainPageObject.assertElementNotPresent(By.xpath(search_result_locator), "Found some results");

    }


    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic rearching by " + search_line,
                15
        );

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);

    }


    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObject.skipOnboarding(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find 'Skip' button", 5);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        driver.runAppInBackground(Duration.ofSeconds(2));

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background",
                5
        );


    }




}