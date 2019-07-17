package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.AutomationDriver;
import util.DriverUtils;

public class HomePage {

    private static By lnk_homeInsurance = By.xpath("//div[@id='menu-slider-carousel']//a[contains(text(),'Home Insurance ')]");
    private static By btn_buyNow = By.xpath("//button[text()=' Buy Now']");
    private WebDriver driver;
    private DriverUtils driverUtils;

    public HomePage() {
        driver = AutomationDriver.getDriver();
        driverUtils = new DriverUtils();
    }


    public void launchHDFCErgo(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void clickHomeInsuranceLink() {
        driverUtils.getClickableElement(lnk_homeInsurance).click();
    }

    public void clickBuyNow() {
        driverUtils.getClickableElement(btn_buyNow).click();
    }


}
