package util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import base.BaseTester;

public class SeleniumUtil {

	private static Logger logger = Logger.getLogger(BaseTester.class);

	/**
	 * 打开浏览器方法
	 * @param browserType
	 * @param driverPath
	 * @return
	 */
	public static WebDriver openBrowser(String browserType, String driverPath) {
		WebDriver driver = null;
		if ("ie".equals(browserType)) {
			driver = openIEBrowser(driverPath);
		} else if ("Chrome".equals(browserType)) {
			driver = openChromeBrowser(driverPath);
		} else if ("Firefox".equals(browserType)) {
			// 打开Firefox浏览器
		}
		return driver;
	}

	/**
	 * 打开Chrome浏览器
	 * @param driverPath
	 * @return
	 */
	private static WebDriver openChromeBrowser(String driverPath) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
		return new ChromeDriver();
	}

	/**
	 * 打开IE浏览器
	 * @param driverPath
	 * @return
	 */

	private static WebDriver openIEBrowser(String driverPath) {
		// 设置可执行驱动的路径
		System.setProperty(InternetExplorerDriverService.IE_DRIVER_ENGINE_PROPERTY, driverPath);
		// driver期望的能力
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// 忽略IE浏览器的安全设置
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// 忽略页面缩放
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		// 指定一个初始URL，防止Window对象丢失
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "https://www.2345.com/?k23973");
		// 创建一个有期望能力的driver对象
		return new InternetExplorerDriver(capabilities);
	}
}
