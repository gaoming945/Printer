package com.ufgov.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件读取
 */
public class Props {
	//存储配置的位置信息
	private static String config;
	//存储内容信息
	private static String position;
	
	private static final Logger logger = LoggerFactory.getLogger(Props.class);

	static {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
			config = prop.getProperty("config").trim();
			position = prop.getProperty("position").trim();
			logger.info("now {}", "读取config.properties配置文件");
		} catch (IOException e) {
			logger.error("now {}", "读取config.properties配置文件发生IO错误");
			e.printStackTrace();
		}
	}

	public static String getConfig() {
		return config;
	}

	public static void setConfig(String config) {
		Props.config = config;
	}

	public static String getPosition() {
		return position;
	}

	public static void setPosition(String position) {
		Props.position = position;
	}

}
