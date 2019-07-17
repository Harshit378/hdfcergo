package util;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AutomationDriver {

    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        AutomationDriver.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browserType) throws MalformedURLException {
        if ("IExplorer".equalsIgnoreCase(browserType)) {
            System.setProperty("webdriver.ie.driver",
                    "F:\\Projects\\TestNG_Practice\\src\\main\\resources\\drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        } else if ("Chrome".equalsIgnoreCase(browserType)) {
            System.setProperty("webdriver.chrome.driver",
                    "F:\\Projects\\TestNG_Practice\\src\\main\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver(createAutomatableDriver());
        } else if ("chrome".equalsIgnoreCase(browserType)) {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setBrowserName(browserType);
            dc.setPlatform(Platform.WIN10);
            driver = new RemoteWebDriver(new URL("http://192.168.56.1:5566/wd/hub"), dc);
        } else if ("firefox".equalsIgnoreCase(browserType)) {
            FirefoxOptions ffo = new FirefoxOptions();
            ffo.setCapability("browserName", browserType);
            ffo.setCapability("marionette", true);
            ffo.setCapability("platformName", Platform.WIN10);
            driver = new RemoteWebDriver(new URL("http://192.168.56.1:5567/wd/hub"), ffo);
        }
        setDriver(driver);
        driver.get("chrome://version/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private ChromeOptions createAutomatableDriver() {

        String downloadFilepath = System.getProperty("user.dir") + "/testng/src/main/resources/downloads";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("-start-maximized");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return chromeOptions;
    }

    @AfterSuite
    public void cleanUp() throws IOException {
        driver.close();
        driver.quit();
        Runtime.getRuntime().exec(new String[]{"cmd", "/K", "TASKKILL /IM chromedriver.exe /F"});
    }
}
