package com.vida.signapp.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.utils.ScreenshotUtil;
import com.pom_classes.DataProviderClass;
import com.pom_classes.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        loginPage = new LoginPage(driver);

    }
    @Test(dataProvider = "loginData", dataProviderClass = DataProviderClass.class)
    public void testLogin(String username, String password) throws Exception {
        loginPage.setUsername(username);
        Thread.sleep(5000);
        loginPage.setPassword(password);
        Thread.sleep(5000);
        loginPage.clickLogin();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
