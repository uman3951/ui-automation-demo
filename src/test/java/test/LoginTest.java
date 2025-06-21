package test;

import org.example.browserfactory.WebDriverFactory;
import org.example.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;


    @BeforeClass
    public void setUp() {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.initBrowser("https://the-internet.herokuapp.com/login", "chrome");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        String message = loginPage.getFlashMessage();
        Thread.sleep(1000);
        Assert.assertTrue(message.contains("You logged into a secure area!"), "Login message mismatch!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
