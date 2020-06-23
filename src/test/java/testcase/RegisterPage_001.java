package testcase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTester;
import util.ExcelUtil;

/**
 * 基础测试类:其他子类只要继承该父类就可以直接打开浏览器
 * @author Administrator<br>
 * @date 2018年6月11日
 * @email 598927012@qq.com
 * @desc
 */
public class RegisterPage_001 extends BaseTester {

	@Test(dataProvider = "getDatas")
	public void f1(String mobilePhone, String password, String pwdconfirm, String expectedTips) {
		to("registerURL");

		typeWord("手机号输入框", mobilePhone);
		typeWord("密码输入框", password);
		typeWord("密码确认框", pwdconfirm);

		click("注册按钮");

		assertTextPresent("提示信息", expectedTips);
	}

	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = ExcelUtil.readExcel("/register.xls", 0, 2, 7, 1, 4);
		return datas;
	}
}
