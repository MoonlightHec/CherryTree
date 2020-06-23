package plm;


import org.testng.annotations.Test;

import base.PlmLoginTester;

public class SampleDevelopListQuery extends PlmLoginTester {

	@Test
	private void say() {
		System.out.println("---------------1------------");

	}

	@Test(enabled = true)
	private void APMQuery() {
		to("plmSampleDevelop");

		typeWord("样品编码搜索输入框", "Y1913001417");
//		click("查询按钮");
		click("高级搜索按钮");
//		typeWord("APM筛选输入框", "yuqiqi");
//		String APMString = getElement("APM下拉列表").getText();
//		assertEquals(APMString, "余琪琪yuqiqi");
	}
}
