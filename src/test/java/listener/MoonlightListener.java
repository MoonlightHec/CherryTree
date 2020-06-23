package listener;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import util.ScreenshotUtil;

/**
 * 
 * @author Administrator<br>
 * @date 2019年5月10日
 * @email 598927012@qq.com
 * @desc 测试用例执行失败时的操作
 */
public class MoonlightListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		System.out.println("-----------发现测试用例执行失败------------");
		super.onTestFailure(tr);

		// 得到suit存放的目录
		String outputDirectory = tr.getTestContext().getOutputDirectory();
		String suitfireDir = outputDirectory.substring(0, outputDirectory.lastIndexOf("\\"));
		// 得到suit套件中test的name
		String testName = tr.getTestContext().getCurrentXmlTest().getName();
		// 拼接出截屏保存的路径
		String screenshotDir = suitfireDir + "\\" + "screenshot" + "\\" + testName;
		// 使用工具类进行截图
		String screenshotFileUrl = ScreenshotUtil.takeScreenshot(screenshotDir);
		// 拼接imageUrl
		// screenshotFileUrl=D:\eclipse-workspace\web_auto_moonlight\target\surefire-reports\screenshot\register\1557571625705.jpg
		// 需要拼接的imageUrl=file://D:/eclipse-workspace/web_auto_moonlight/target/surefire-reports/screenshot/register/1557567352068.png

		// 本地测试地址
		// String baseURL = "file://D:/eclipse-workspace/web_auto_moonlight/target/surefire-reports/";
		// 服务器上的地址
		String baseURL = "http://47.98.175.247:7777/";
		String oldChar = screenshotFileUrl.substring(0, screenshotFileUrl.indexOf("screenshot"));
		String urlTemp = screenshotFileUrl.replace(oldChar, baseURL);
		String imageUrl = urlTemp.replace("\\", "/");

		// 在报表中展示的图片标签
		String imageLogTag = "<img src='" + imageUrl + "' hight='100' width='100'><a href='" + imageUrl
				+ "' target='_blank'>点击查看大图</a></img>";

		Reporter.log(imageLogTag);
	}

	public static void main(String[] args) {
		String screenshotFileUrl = "D:\\eclipse-workspace\\web_auto_moonlight\\target\\surefire-reports\\screenshot\\register\\1557665590287.jpg";
		String baseURL = "http://47.98.175.247:7777/";
		String oldChar = screenshotFileUrl.substring(0, screenshotFileUrl.indexOf("screenshot"));
		String urlTemp = screenshotFileUrl.replace(oldChar, baseURL);
		String imageUrl = urlTemp.replace("\\", "/");

		// 在报表中展示的图片标签
		String imageLogTag = "<img src='" + imageUrl + "' hight='100' width='100'><a href='" + imageUrl
				+ "' target='_blank'>点击查看大图</a></img>";
		System.out.println(imageLogTag);
	}
}
