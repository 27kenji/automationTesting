import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Ex9 extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "language", 15);
        int count_of_articles = SearchPageObject.getCountOfArticlesByTitleAndSubtitle("Java", "language", 15);
        assertTrue("There are less than 3 articles in search result",count_of_articles >= 3);

    }

}