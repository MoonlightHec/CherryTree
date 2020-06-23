package base;

import org.testng.annotations.BeforeTest;

public class PlmLoginTester extends BaseTester {

	@BeforeTest
	public void beforeTest() {

		
		to("plmSampleDevelop");

		typeWord("用户名输入框", "yuqiqi", PlmLoginTester.class);
		typeWord("密码输入框", "123456", PlmLoginTester.class);

		click("登录按钮", PlmLoginTester.class);
	}

	protected void APMQurry() {

	}

}
