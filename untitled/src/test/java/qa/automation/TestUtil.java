package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    private String url; // = "https://www.saucedemo.com/";
    private String browser;
    private int implicitWait;


    @BeforeMethod
    public void setUp(){
        setupBrowserDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    private void setupBrowserDriver(){
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")){
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            browser = config.getProperty("browser");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));

        }catch (IOException e){
            System.out.println("Cannot read configs");
        }
        switch (browser){
            case "chrome":
                createChromeDriver(url, implicitWait);
                break;
            case "firefox":
                createFirefoxDriver(url, implicitWait);
                break;
            default:
                throw new IllegalStateException("Unsupported browser type");
        }
    }


    private void loadUrl(String url){
        driver.get(url);
    }

    private void createChromeDriver(String url, int implicitWait){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        loadUrl(url);
    }

    private void createFirefoxDriver(String url, int implicitWait){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        loadUrl(url);
    }
}