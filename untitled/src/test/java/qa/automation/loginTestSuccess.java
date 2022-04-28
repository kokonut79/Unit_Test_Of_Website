package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class loginTestSuccess {
    private WebDriver driver;



    @BeforeTest
    public void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @DataProvider (name = "CorrectloginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce"}
        };
    }

    @Test (dataProvider = "CorrectloginData")
    public void loginSuccess(String username, String password) {
        driver.get("https://www.saucedemo.com/");

        WebElement user_name = driver.findElement(By.id("user-name"));
        user_name.click();
        user_name.sendKeys(username);




        WebElement password_input = driver.findElement(By.id("password"));
        password_input.click();
        password_input.sendKeys(password);

        WebElement login_button = driver.findElement(By.className("btn_action"));
        login_button.click();



        WebElement showBUtton = driver.findElement(By.id ("react-burger-menu-btn"));

        Assert.assertTrue(showBUtton.isDisplayed());
    }



    }




