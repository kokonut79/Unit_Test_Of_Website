package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WrongLoginData {
    private WebDriver driver;

    @BeforeTest
    public void initializeDriver() {
        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();

    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

    @DataProvider(name = "WrongLoginData")
    public Object[][] getWrongLoginData() {
        return new Object[][]{
                {"alone", "afasdf"}
        };

    }
    @Test (dataProvider = "WrongLoginData")
    public void WrongLoginData(String user_name, String password) {
        driver.get("http://www.saucedemo.com/");
        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys(user_name);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("[value=Login]"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[text()='Epic sadface: Username and password do not match any user in this service']"));

        Assert.assertTrue(errorMessage.isDisplayed());
    }
}
