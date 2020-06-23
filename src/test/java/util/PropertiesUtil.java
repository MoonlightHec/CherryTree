package util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	static Properties urlProperties = null;
	static Properties excelProperties = null;

	static {
		loadProperties();
	}

	private static void loadProperties() {
		urlProperties = new Properties();
		excelProperties = new Properties();
		try {
			urlProperties.load(PropertiesUtil.class.getResourceAsStream("/url.properties"));
			excelProperties.load(PropertiesUtil.class.getResourceAsStream("/soccermaster/excel.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getUrl(String urlKey) {
		return urlProperties.getProperty(urlKey);
	}

	public static String getExcel(String excelKey) {
		return excelProperties.getProperty(excelKey);
	}

	public static void main(String[] args) {

	}

}
