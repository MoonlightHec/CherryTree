package plm;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.PlmLoginTester;

public class PlmFabricApply extends PlmLoginTester {

	@Test
	private void fabricApplyAPMQuery() {
		to("plmFabricApply");
		typeWord("设计师查询框", "yuqiqi");
		WebElement apmElement = getElement("设计师查询下拉框");
		apmElement.click();
		click("查询按钮");
		String actrual = apmElement.getText();

		
		
		assertEquals(actrual, "余琪琪(yuqiqi)");
	}
}
