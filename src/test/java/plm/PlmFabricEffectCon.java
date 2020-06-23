package plm;

import org.testng.annotations.Test;

import base.PlmLoginTester;

public class PlmFabricEffectCon extends PlmLoginTester {
	@Test
	private void FabricEffectConAPMQurry() {
		to("plmFabricEffectCon");

		typeWord("设计师输入框","bin");
//		click("设计师选项");
//		click("查询按钮");

		String numString = getElement("本页数据量").getText();
		System.out.println("yeshu "+numString);
		logger.info("noString");
	}
}
