package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomeInsurancePage;
import pages.HomePage;
import util.AutomationDriver;

public class HDFCErgoTest {

    private String baseURL = "https://www.hdfcergo.com";
    private HomePage hp;
    private HomeInsurancePage hip;

    @BeforeClass
    public void launchAUT() {
        AutomationDriver.getDriver().get(baseURL);
        hp = new HomePage();
        hip = new HomeInsurancePage();
    }

    @Test(priority = 1)
    public void launchApplication() {
        hp.launchHDFCErgo(baseURL);
    }

    @Test(priority = 2)
    public void navigateToHomeInsurance() {
        hp.clickHomeInsuranceLink();
        hp.clickBuyNow();
    }

    @Test(priority = 3)
    public void selectHomeCover() {
        hip.switchToHomeShieldInsuranceJourneyHomePage();
        hip.selectHomeCover();
    }


}
