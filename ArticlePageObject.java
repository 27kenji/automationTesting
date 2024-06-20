package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE = "//*[@text='Java (programming language)']",
    TITLE_REPLACE = "//*[@text='{SUBSTRING}']",
    FOOTER_ELEMENT = "//*[@text='View article in browser']",
    SAVE_BUTTON = "org.wikipedia:id/page_save",
    ADD_TO_LIST_BUTTON = "//android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    ADD_TO_SAVED_LIST_BUTTON = "//*[@text='{SUBSTRING}']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchTitle(String substring) {
        return TITLE_REPLACE.replace("{SUBSTRING}", substring);
    }

    private static String getResultSavedArticleTitle(String name_of_folder) {
        return ADD_TO_SAVED_LIST_BUTTON.replace("{SUBSTRING}", name_of_folder);

    }
    /* TEMPLATES METHODS */

    /*public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE),
                "Cannot find article title on page", 15);

    }*/

    public WebElement waitForTitleElementAndReplace(String substring) {
        String search_result_title = getResultSearchTitle(substring);
        return this.waitForElementPresent(By.xpath(search_result_title), "Cannot find title with substring " + substring, 10);

    }

    /*public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }*/

    public String getArticleTitleAndReplace(String substring) {
        WebElement title_element = waitForTitleElementAndReplace(substring);
        return title_element.getAttribute("text");
    }


    public void swipeToFooter() {
        this.testSwipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 10);
    }

    public void swipeUp() {
        this.testSwipeUpArticle(2000);
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(By.id(SAVE_BUTTON), "Cannot find button to save article", 5);
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button", 5);
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT), name_of_folder,
                "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "Cannot press OK button", 5);

    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article", 5);
    }

    public void secondAddArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(By.id(SAVE_BUTTON), "Cannot find button to save article", 5);
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button", 5);
        String element = getResultSavedArticleTitle(name_of_folder);
        this.waitForElementAndClick(By.xpath(element), "Cannot find saved list", 15);

    }


    public void articleTitleIsDisplayed(String title) {
        String element = getResultSearchTitle(title);
        this.assertElementPresent(By.xpath(element));
    }
}
