package com.ufgov.vo;

/**
 * @ClassName: Config
 * @Description:Config配置文件
 * @author gaoming gaomingd@yonyou.com
 * @date 2014-12-20 1:30:35
 */
public class Config {

	// xml关联字段
	private String code;
	// 题目
	private String title;
	// X坐标
	private int x;
	// Y坐标
	private int y;

	public Config(String title, int x, int y) {
		super();
		this.title = title;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Config [code=" + code + ", title=" + title + ", x=" + x
				+ ", y=" + y + "]";
	}

	public Config() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
