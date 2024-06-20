package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class OnboardingPageObject extends MainPageObject{
    private static final String
    SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button";

    public OnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void skipOnboarding() {
        this.skipOnboarding(By.id(SKIP_BUTTON), "Cannot click on 'Skip' button", 5);

    }
}
