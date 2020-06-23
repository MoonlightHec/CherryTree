package util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import base.BaseTester;

/**
 * 
 * @author Administrator<br>
 * @date 2019年5月10日
 * @email 598927012@qq.com
 * @desc 截屏工具类
 */
public class ScreenshotUtil {

	private static Logger logger = Logger.getLogger(ScreenshotUtil.class);

	@Deprecated
	public static String takeScreenshot2(String screenshotDir) {
		// logger.info(screenshotDir);
		WebDriver driver = BaseTester.getDriver();
		Date date = new Date();
		long time = date.getTime();
		String filename = screenshotDir + "\\" + time + ".jpg";

		File screenshotFile = null;

		if (driver instanceof ChromeDriver) {
			ChromeDriver chromeDriver = (ChromeDriver) driver;
			// 截图，这是源文件
			screenshotFile = chromeDriver.getScreenshotAs(OutputType.FILE);

		} else if (driver instanceof FirefoxDriver) {
			FirefoxDriver firefoxDriver = (FirefoxDriver) driver;
			screenshotFile = firefoxDriver.getScreenshotAs(OutputType.FILE);
		}
		// 创建目标文件
		File destFile = new File(filename);
		try {
			logger.info("拷贝截屏到目标路径中去");
			logger.info(filename);
			FileUtils.copyFile(screenshotFile, destFile);
		} catch (IOException e) {
			logger.info(screenshotFile);
			e.printStackTrace();

		}
		return filename;
	}

	public static String takeScreenshot(String screenshotDir) {
		logger.info(screenshotDir);
		WebDriver driver = BaseTester.getDriver();
		Date date = new Date();
		long time = date.getTime();
		String filename = screenshotDir + "\\" + time + ".jpg";

		File screenshotFile = null;

		// if (driver instanceof ChromeDriver) {
		// ChromeDriver chromeDriver = (ChromeDriver) driver;
		// // 截图，这是源文件
		// screenshotFile = chromeDriver.getScreenshotAs(OutputType.FILE);
		//
		// } else if (driver instanceof FirefoxDriver) {
		// FirefoxDriver firefoxDriver = (FirefoxDriver) driver;
		// screenshotFile = firefoxDriver.getScreenshotAs(OutputType.FILE);
		// }

		// 将各种Driver转变成接口，用接口调用getScreenshotAs。面向接口编程
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		// 创建目标文件
		File destFile = new File(filename);
		try {
			logger.info("拷贝截屏到目标路径中去");
			logger.info(filename);
			FileUtils.copyFile(screenshotFile, destFile);
		} catch (IOException e) {
			logger.info(screenshotFile);
			e.printStackTrace();
		}
		return filename;
	}
}
