package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CSVHelper;
public class CheckoutTest {

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

    @DataProvider(name = "CorrectloginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"}
        };
    }
    //MAKE DATA PROVIDER NAMED as userDAta
    @DataProvider(name = "userData")
    public Object[][] UserData() {
        return new Object[][]{
                {"atanas", "porki", "4000"},
        };
    }


    @Test(dataProvider = "CorrectloginData")
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

        WebElement add_item_button = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        add_item_button.click();

        WebElement add_second_Item = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        add_second_Item.click();



        WebElement cart_button = driver.findElement(By.className("shopping_cart_link"));
        cart_button.click();


        WebElement checkout_button = driver.findElement(By.className("btn btn_action btn_medium checkout_button"));
        checkout_button.click();

        WebElement first_name = driver.findElement(By.id("first-name"));
        first_name.click();
        first_name.sendKeys("Atanas");

        WebElement last_name = driver.findElement(By.id("last-name"));
        last_name.click();
        last_name.sendKeys("Porki");

        WebElement post_code = driver.findElement(By.id("postal-code"));
        post_code.click();
        post_code.sendKeys("4000");


        WebElement continue_button = driver.findElement(By.className("submit-button btn btn_primary cart_button btn_action"));
        continue_button.click();

        WebElement Finish_button = driver.findElement(By.className("btn btn_action btn_medium cart_button"));
        Finish_button.click();




        WebElement showBUtton = driver.findElement(By.className("pony_express"));

        Assert.assertTrue(showBUtton.isDisplayed());
    }
}
