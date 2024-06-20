
import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;

import lib.ui.SearchPageObject;
import org.junit.Test;


public class Ex3 extends CoreTestCase {

    @Test
    public void testCountOfArticles() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        int elements = SearchPageObject.getCountOfArticles();
        assertTrue("Cannot find several articles", elements > 1);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

}




