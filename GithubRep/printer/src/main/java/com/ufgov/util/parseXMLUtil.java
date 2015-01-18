package com.ufgov.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class parseXMLUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(parseXMLUtil.class);

	public static Document XML2Doc(String xmlPath) {
		Document document = null;
		try {
			logger.debug("【开始】读取" + xmlPath + ".xml配置文件");
			InputStream in = new FileInputStream(xmlPath);
			document = new SAXReader().read(in);
		} catch (DocumentException e) {
			logger.error("【错误】读取" + xmlPath + ".xml配置文件发生错误，文件解析异常");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error("【错误】读取" + xmlPath + ".xml路径存在错误");
			e.printStackTrace();
		}
		return document;
	}
}
