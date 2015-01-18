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

public class ParseConfigXML {
	private static final Logger logger = LoggerFactory.getLogger(ParseConfigXML.class);
	public static Object[] ReadXml(String xmlPath) {
		return parseXML(parseXMLUtil.XML2Doc(xmlPath).getRootElement());
	}

	private static Object[] parseXML(Element rootElement) {

		Object[] list = new Object[2];

		logger.debug("【开始】解析config.xml配置文件-->config信息");
		@SuppressWarnings("unchecked")
		List<Element> lConfigs = rootElement.element("items").elements("item");
		Map<String, Config> mapConfigs = new HashMap<String, Config>();
		for (Element element : lConfigs) {
			String code = element.elementText("code");
			String title = element.elementText("title");
			Config config = new Config();
			config.setTitle(title);

			mapConfigs.put(code, config);
		}
		list[0] = mapConfigs;
		logger.debug("【结束】解析config.xml配置文件-->config信息");

		logger.debug("【开始】解析config.xml配置文件-->product信息");
		@SuppressWarnings("unchecked")
		List<Element> lProducts = rootElement.element("products").elements(
				"product");
		HashMap<String, Product> mapProducts = new HashMap<String, Product>();

		for (Element element : lProducts) {
			String code = element.elementText("code");
			String descrlption = element.elementText("descrlption");
			String packing = element.elementText("packing");
			String unit = element.elementText("unit");
			String qty = element.elementText("qty");
			String unitPrice = element.elementText("unitPrice");
			String amount = element.elementText("amount");
			String others = element.elementText("others");

			Product product = new Product();
			product.setCode(code);
			product.setDescrlption(descrlption);
			product.setPacking(packing);
			product.setUnit(unit);
			product.setQty(qty);
			product.setUnitPrice(unitPrice);
			product.setAmount(amount);
			product.setOthers(others);

			mapProducts.put(code, product);
		}
		list[1] = mapProducts;
		logger.debug("【结束】解析config.xml配置文件-->product信息");
		
		return list;
	}
}
