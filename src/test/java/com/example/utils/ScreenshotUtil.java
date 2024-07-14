package com.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotUtil {
    private static final Logger logger = Logger.getLogger(ScreenshotUtil.class.getName());

    public static String takeScreenshot(WebDriver driver, String screenshotName)
    {
        // Create the screenshot directory if it does not exist
        String screenshotsDir = System.getProperty("user") + "/Screenshots/";
        File directory = new File(screenshotsDir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Generate the file name with the current date
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = screenshotsDir + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);

        try {
            FileHandler.copy(source, finalDestination);
            logger.info("Screenshot taken: " + destination);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception while taking screenshot", e);
        }

        return destination;
    }

}
