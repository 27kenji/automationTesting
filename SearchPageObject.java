package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageObject extends MainPageObject{
    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUB}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
    SEARCH_ARTICLE_BY_TWO_LOCATORS = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, '{SUBSTRING1}')][contains(@text,'{SUBSTRING2}')]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUB}", substring);
    }

    public static String getResultSearchElementByTwoLocators(String title, String description) {
        String locator =  SEARCH_ARTICLE_BY_TWO_LOCATORS.replace("{SUBSTRING1}", title).replace("{SUBSTRING2}", description);
        return locator;
    }

    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 10);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 10);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request ", 15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 10);

    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public int getCountOfArticles() {
        return this.countOfArticles(By.xpath(SEARCH_RESULT_ELEMENT), 15);
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description, long timeout) {
        String locator = getResultSearchElementByTwoLocators(title, description);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage("Cannot find element by two locators");
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public int getCountOfArticlesByTitleAndSubtitle(String title, String description, long timeout) {
        String locator = getResultSearchElementByTwoLocators(title, description);
        return this.countOfArticles(By.xpath(locator), 15);

    }

}
