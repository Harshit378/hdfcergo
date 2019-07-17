package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.AutomationDriver;
import util.DriverUtils;

import java.util.Set;

public class HomeInsurancePage {

    private static By drp_homeCoverFor = By.cssSelector("#HomeCoverSpan");
    private static By opt_aHomeOwner = By.xpath("//span[@name='OwnerShip' and text()='A Home Owner']");
    private static By drp_propertyToCover = By.cssSelector("#RiskCoverSpan");
    private static By opt_structure = By.cssSelector("#Structure");
    private static By btn_continue = By.xpath("//button[text()='Continue']");
    private final WebDriver driver;
    private final DriverUtils driverUtils;

    public HomeInsurancePage() {
        driver = AutomationDriver.getDriver();
        driverUtils = new DriverUtils();
    }

    public void switchToHomeShieldInsuranceJourneyHomePage() {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!currentWindow.equalsIgnoreCase(window)) {
                driver.switchTo().window(window);
            }
        }
    }

    public void selectHomeCover() {
        driverUtils.getClickableElement(drp_homeCoverFor).click();
        driverUtils.getClickableElement(opt_aHomeOwner).click();
        driverUtils.getClickableElement(drp_propertyToCover).click();
        driverUtils.clickUsingJavaScriptExecutor(opt_structure);
        driverUtils.getClickableElement(btn_continue).click();
    }


}
