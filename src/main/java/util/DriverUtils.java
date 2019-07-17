package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

public class DriverUtils {

    private WebDriver driver;

    public DriverUtils() {
        driver = AutomationDriver.getDriver();
    }

    public WebElement getClickableElement(By locator) {
        WebElement clickableElement;
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        clickableElement = fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return clickableElement;
    }

    public void clickUsingJavaScriptExecutor(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", getClickableElement(locator));
    }

}
