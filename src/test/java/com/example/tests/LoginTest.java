package com.example.tests;

import com.example.utils.ExcelUtils;
import com.example.utils.ScreenshotUtils;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginTest extends BaseTest{
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws IOException {
        List<String[]> data = ExcelUtils.getData("src/resources/loginData.xlsx", "Sheet1");
        Object[][] dataArray = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }
        return dataArray;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        try {
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.findElement(By.id("username me")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("submit")).click();

            // Add your assertions here
        } catch (Exception e) {
            ScreenshotUtils.takeScreenshot(driver, "testLogin");
            throw e;
        }
    }
}
