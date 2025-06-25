package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public static WebDriver driver;
    public Properties p;
    public Logger logger;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException, InterruptedException {
        logger = LogManager.getLogger(this.getClass());

        // Load properties file
        try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
            p = new Properties();
            p.load(file);
        } catch (Exception e) {
            logger.error("Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("config.properties not found or invalid.");
        }

        String env = p.getProperty("execution_env").toLowerCase();

        try {
            if (env.equals("remote")) {
                // Use Options instead of DesiredCapabilities
                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.setPlatformName(os);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                } else if (browser.equalsIgnoreCase("edge")) {
                    EdgeOptions options = new EdgeOptions();
                    options.setPlatformName(os);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                } else {
                    throw new RuntimeException("Unsupported browser: " + browser);
                }

            } else if (env.equals("local")) {
                if (browser.equalsIgnoreCase("chrome")) {
                    driver = new org.openqa.selenium.chrome.ChromeDriver();
                } else if (browser.equalsIgnoreCase("edge")) {
                    driver = new org.openqa.selenium.edge.EdgeDriver();
                } else {
                    throw new RuntimeException("Unsupported browser: " + browser);
                }
            } else {
                throw new RuntimeException("Invalid execution_env value: " + env);
            }

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(p.getProperty("appURL"));
        } catch (MalformedURLException e) {
            logger.error("Grid URL malformed: " + e.getMessage());
            throw new RuntimeException("RemoteWebDriver URL is incorrect.");
        } catch (Exception e) {
            logger.error("Driver setup failed: " + e.getMessage());
            throw new RuntimeException("Driver setup failed", e);
        }
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }

    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomeNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomeAlphaNumberic() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File target = new File(destPath);
        FileHandler.copy(source, target);
        return destPath;
    }
}