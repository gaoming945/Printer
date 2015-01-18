package com.ufgov.prop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufgov.util.parseXMLUtil;
import com.ufgov.vo.Config;
import com.ufgov.vo.Product;

public class ParsePositionXML {
	private static final Logger logger = LoggerFactory
			.getLogger(ParseConfigXML.class);

	public static Object[] ReadXml(String xmlPath) {
		return parseXML(parseXMLUtil.XML2Doc(xmlPath).getRootElement());
	}

	@SuppressWarnings("unchecked")
	private static Object[] parseXML(Element rootElement) {
		Object[] map = new Object[2];

		logger.debug("【开始】解析position.xml配置文件-->config信息");
		List<Element> lConfigs = rootElement.element("items").elements("item");
		Map<String, Config> mapConfigs = new HashMap<String, Config>();
		for (Element element : lConfigs) {
			String code = element.elementText("code");
			String x = element.elementText("x");
			String y = element.elementText("y");

			Config config = new Config();
			config.setX(Integer.parseInt(x));
			config.setY(Integer.parseInt(y));

			mapConfigs.put(code, config);
		}
		logger.debug("【结束】解析position.xml配置文件-->config信息");
		map[0] = mapConfigs;

		logger.debug("【开始】解析position.xml配置文件-->product信息");
		List<Element> lProducts = rootElement.element("products").elements(
				"product");
		HashMap<String, Product> mapProducts = new HashMap<String, Product>();
		for (int i = 0; i < lProducts.size(); i++) {
			Product product = new Product();
			String code = lProducts.get(i).elementText("code");
			String x = String.valueOf((Integer.parseInt(lProducts.get(i)
					.elementText("x"))));
			String y = String.valueOf((Integer.parseInt(lProducts.get(i)
					.elementText("y"))));
			product.setX(Integer.parseInt(x));
			product.setY(Integer.parseInt(y));
			mapProducts.put(code, product);
		}
		logger.debug("【结束】解析position.xml配置文件-->product信息");
		map[1] = mapProducts;

		return map;
	}
}
