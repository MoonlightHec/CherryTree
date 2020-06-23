package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.testng.log4testng.Logger;

import base.BaseTester;
import base.Locator;

public class PagesUtil {
	
	protected static Logger logger = Logger.getLogger(PagesUtil.class);
	public static Map<String, Map<String, Locator>> pageMap = null;

	static {
		loadPages();
	}

	private static Map<String, Map<String, Locator>> loadPages() {
		logger.info("-------------开始加载页面元素---------------");
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(PagesUtil.class.getResourceAsStream("/plmpages.xml"));
			Element rootElement = document.getRootElement();
			List<Element> pageList = rootElement.elements("page");
			pageMap = new HashMap<String, Map<String, Locator>>();
			for (Element pageElement : pageList) {
				List<Element> locatorList = pageElement.elements("locator");
				String pageName = pageElement.attributeValue("name");
				Map<String, Locator> locatorMap = new HashMap<String, Locator>();
				for (Element locatorElement : locatorList) {
					String by = locatorElement.attributeValue("by");
					String value = locatorElement.attributeValue("value");
					String desc = locatorElement.attributeValue("desc");
					Locator locator = new Locator(by, value, desc);
					locatorMap.put(desc, locator);
				}
				pageMap.put(pageName, locatorMap);

			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		logger.info("-------------页面数据加载完成---------------");
		return pageMap;
	}

	public static void main(String[] args) {
		Map<String, Map<String, Locator>> pageMap = loadPages();
		Map<String, Locator> locatorMap = pageMap.get("plm.LoginTester");
		Locator locator = locatorMap.get("用户名输入框");
		System.out.println(locator.toString());

	}

}
