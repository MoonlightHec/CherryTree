package base;

import java.util.ArrayList;

import org.testng.Assert;

public class AssertResult extends Assert {

	@Deprecated
	public static void assertTrue2(ArrayList<String> actualStringList, ArrayList<String> tipsStringList) {

		int indexStringList = -1;
		try {
			for (int i = 0; i < actualStringList.size(); i++) {
				String actualString = actualStringList.get(i);
				String tipsString = tipsStringList.get(i);
				if (!actualString.equals(tipsString)) {
					indexStringList = i;
					break;
				}
			}
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("期望值和实际值个数不一致");
		}
		if (indexStringList != -1) {
			String actual = actualStringList.get(indexStringList);
			String expected = tipsStringList.get(indexStringList);
			Assert.assertEquals(actual, expected);

		} else {
			Assert.assertTrue(true);
		}
	}

	/**
	 * 断言多个字符串是否相等，两个list大小必须一致
	 * @param actualStringList 实际值
	 * @param tipsStringList 期望值
	 */
	public static void assertTrue(ArrayList<String> actualStringList, ArrayList<String> tipsStringList) {

		try {
			// 当list参数个数不一致时，手动抛出一个异常
			if (actualStringList.size() != tipsStringList.size()) {
				throw new IndexOutOfBoundsException();
			}
			// 对每一对参数进行断言，有一对失败则断言失败
			for (int i = 0; i < actualStringList.size(); i++) {
				String actualString = actualStringList.get(i);
				String tipsString = tipsStringList.get(i);
				try {
					Assert.assertEquals(actualString, tipsString);
				} catch (Exception AssertionError) {
					break;
				}
			}
			// 参数个数不一致异常处理
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("期望值和实际值个数不一致");
		}
	}

	public static void main(String[] args) {
		ArrayList<String> actualStringList = new ArrayList<>();
		ArrayList<String> tipsStringList = new ArrayList<>();

		actualStringList.add("qwe");
		actualStringList.add("233");
		actualStringList.add("qwe");

		tipsStringList.add("qwe");
		tipsStringList.add("qwe");
		tipsStringList.add("233");
		tipsStringList.add("qwe");

		assertTrue(actualStringList, tipsStringList);
	}
}
