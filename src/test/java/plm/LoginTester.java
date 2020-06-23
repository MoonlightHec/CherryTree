package plm;

import org.testng.annotations.Test;

import base.BaseTester;

public class LoginTester extends BaseTester {

	@Test
	public void loginTester() {

		to("plmPath");
		 //to("plmUserLogin");

		typeWord("用户名输入框", "yuqiqi");
		typeWord("密码输入框", "123456");

		click("登录按钮");
	}

}
