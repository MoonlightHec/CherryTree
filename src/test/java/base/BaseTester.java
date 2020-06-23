package base;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import bsh.This;
import util.ExcelUtil;
import util.PagesUtil;
import util.PropertiesUtil;
import util.SeleniumUtil;

/**
 * 基础测试类:其他子类只要继承该父类就可以直接打开浏览器
 *
 * @author Administrator<br>
 * @date 2018年6月11日
 * @email 598927012@qq.com
 * @desc
 */
public class BaseTester {

    protected static Logger logger = Logger.getLogger(BaseTester.class);
    protected static WebDriver driver = null;

    /**
     * 套件开始
     *
     * @param browserType 打开浏览器的类型
     * @param driverPath  打开浏览器的路径
     */
    @BeforeSuite
    @Parameters(value = {"browserType", "driverPath"})
    public void beforeSuit(String browserType, String driverPath) {
        driver = SeleniumUtil.openBrowser(browserType, driverPath);
        driver.manage().window().maximize();
    }

    /**
     * 在指定页面查找元素，默认时间5秒
     *
     * @param keyword 关键字
     * @param clazz   指定页面的字节码
     * @return 查找的元素
     */
    protected WebElement getElement(String keyword, Class<?> clazz) {
        return getElement(keyword, 5, clazz);
    }

    /**
     * 在当前页面查找元素
     *
     * @param keyword 关键字
     * @return 查找的元素
     */
    protected WebElement getElement(String keyword) {
        return getElement(keyword, 5, this.getClass());
    }

    /**
     * 在指定页面查找元素
     *
     * @param keyword          关键字
     * @param timeOutInSeconds 等待时间
     * @param clazz            指定页面的字节码
     * @return 查找的元素
     */
    protected WebElement getElement(String keyword, long timeOutInSeconds, Class<?> clazz) {

        // 获得pages.xml中keyword对应的locator
        final Locator locator = PagesUtil.pageMap.get(clazz.getName()).get(keyword);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        // 等待-->直到找到某个元素为止
        logger.info("log-开始查找元素");
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver arg0) {
                By by = null;
                String byString = locator.getBy();
                String value = locator.getValue();
                // if ("id".equals(byString)) {
                // by = By.id(value);
                // } else if ("name".equals(byString)) {
                // by = By.name(value);
                // } else if ("tagName".equals(byString)) {
                // by = By.tagName(value);
                // } else if ("className".equals(byString)) {
                // by = By.className(value);
                // } else if ("linkText".equals(byString)) {
                // by = By.linkText(value);
                // } else if ("partialLinkText".equals(byString)) {
                // by = By.partialLinkText(value);
                // } else if ("cssSelector".equals(byString)) {
                // by = By.cssSelector(value);
                // } else if ("xpath".equals(byString)) {
                // by = By.xpath(value);
                // }

                // 使用反射来获得by
                // 获得一个By的字节码
                Class<?> clazz = By.class;
                try {
                    // 根据字节码获得By的细节，里面有By的各种方法
                    Method byMethod = clazz.getMethod(byString, String.class);
                    // byMethod有一个invoke方法来调用本身，第一个参数传调用该方法的对象，By是静态方法，没有调用对象所以是null，后面传调用方法时本该传的参数。
                    // 可以看做是正常调用了一个By方法，获得一个by对象，by = By.id(value)
                    by = (By) byMethod.invoke(null, value);
                } catch (NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return driver.findElement(by);
            }
        });
        logger.info("找到元素：" + element.getText());
        return element;
    }

    /**
     * 在指定页面查找元素列表
     *
     * @param keyword          关键字
     * @param timeOutInSeconds 等待时间
     * @param clazz            指定页面的字节码
     * @return 查找的元素列表
     */
    protected List<WebElement> getElements(String keyword, long timeOutInSeconds, Class<?> clazz) {
        final Locator locator = PagesUtil.pageMap.get("testcase.RegisterPage").get(keyword);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

        List<WebElement> elementList = wait.until(new ExpectedCondition<List<WebElement>>() {

            @Override
            public List<WebElement> apply(WebDriver arg0) {
                By by = null;
                String byString = locator.getBy();
                String value = locator.getValue();
                if ("id".equals(byString)) {
                    by = By.id(value);
                } else if ("name".equals(byString)) {
                    by = By.name(value);
                } else if ("tagName".equals(byString)) {
                    by = By.tagName(value);
                } else if ("className".equals(byString)) {
                    by = By.className(value);
                } else if ("linkText".equals(byString)) {
                    by = By.linkText(value);
                } else if ("partialLinkText".equals(byString)) {
                    by = By.partialLinkText(value);
                } else if ("cssSelector".equals(byString)) {
                    by = By.cssSelector(value);
                } else if ("xpath".equals(byString)) {
                    by = By.xpath(value);
                }
                return driver.findElements(by);
            }
        });
        return elementList;
    }

    /**
     * 在当前页面查找元素列表
     *
     * @param keyword 关键字
     * @return 查找的元素列表
     */
    protected List<WebElement> getElements(String keyword) {
        return getElements(keyword, 5, this.getClass());
    }

    /**
     * 打开一个链接
     *
     * @param urlKey 链接在url.properties文件中的key
     */
    protected void to(String urlKey) {
        String url = PropertiesUtil.getUrl(urlKey);
        driver.navigate().to(url);
        logger.info("打开链接：" + urlKey + "【" + url + "】");
    }


    /**
     * 向当前页面的输入框输入内容
     *
     * @param keyword       输入框元素的关键字
     * @param contentToSend 要输入的内容
     */
    protected void typeWord(String keyword, String contentToSend) {
        typeWord(keyword, contentToSend, this.getClass());
        logger.info(keyword + "中输入：" + contentToSend + "【" + this.getClass().getName() + "】");
    }

    /**
     * 向指定页面的输入框输入内容
     *
     * @param keyword       输入框元素的关键字
     * @param contentToSend 要输入的内容
     * @param clazz         指定页面的字节码
     */
    protected void typeWord(String keyword, String contentToSend, Class<?> clazz) {
        System.out.println("syso-ceshi ");
        logger.info("log-ceshilog");
        logger.info(keyword + "中开始输入：" + contentToSend + "【" + clazz.getName() + "】");
        WebElement element = getElement(keyword, clazz);
        element.sendKeys(contentToSend);
        logger.info(keyword + "中输入：" + contentToSend + "【" + clazz.getName() + "】");
    }

    /**
     * 向输入框输入内容
     *
     * @param element       输入框元素
     * @param contentToSend 要输入的内容
     */
    protected void typeWord(WebElement element, String contentToSend) {
        element.sendKeys(contentToSend);
    }

    /**
     * 当前页面进行点击操作
     *
     * @param keyword 要点击的元素的关键字
     */
    protected void click(String keyword) {
        click(keyword, this.getClass());
    }

    /**
     * 指定页面进行点击操作
     *
     * @param keyword 关键字
     * @param clazz   指定页面的字节码
     */
    protected void click(String keyword, Class<?> clazz) {
        getElement(keyword, clazz).click();
    }

    /**
     * 获取文本内容
     *
     * @param element 获取文本的元素
     * @return 文本的字符串
     */
    protected String getText(WebElement element) {
        String content = element.getText();
        logger.info("获取元素文本[" + content + "]");
        return content;
    }

    /**
     * 获取文本内容
     *
     * @param keyword 获取文本的元素的关键字
     * @return 文本的字符串
     */
    protected String getText(String keyword) {
        String content = getElement(keyword).getText();
        logger.info(keyword + "的文本内容：[" + content + "]");
        return content;
    }

    /**
     * 断言文本内容是否与期望值相同
     *
     * @param keyword      文本元素的关键字
     * @param expectedTips 期望文本内容
     */
    public void assertTextPresent(String keyword, String expectedTips) {
        String actualTips = getText(keyword);
        assertEquals(actualTips, expectedTips);
    }

    /**
     * 套件结束，关闭浏览器
     */
    @AfterSuite
    public void afterSuite() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @DataProvider(name = "getDatas")
    public Object[][] getDatas() {
        String excelPath = PropertiesUtil.getExcel("excelPath");
        String sheetIndex = PropertiesUtil.getExcel("sheetIndex");
        String startRow = PropertiesUtil.getExcel("startRow");
        String endRow = PropertiesUtil.getExcel("endRow");
        String startCell = PropertiesUtil.getExcel("startCell");
        String endCell = PropertiesUtil.getExcel("endCell");
        Object[][] data = ExcelUtil.readExcel(excelPath, sheetIndex, startRow, endRow, startCell, endCell);
        return data;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
