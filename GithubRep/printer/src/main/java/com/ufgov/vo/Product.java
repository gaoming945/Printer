package com.ufgov.vo;

/**
 * @ClassName: Product
 * @Description: 产品VO
 * @author gaoming gaomingd@yonyou.com
 * @param <E>
 * @date 2014-12-20 1:35:48
 */
public class Product {
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 产品名称
	 */
	private String descrlption;
	/**
	 * 规格型号
	 */
	private String packing;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 数量
	 */
	private String qty;
	/**
	 * 单价
	 */
	private String unitPrice;
	/**
	 * 金额
	 */
	private String amount;
	/**
	 * 备注
	 */
	private String others;

	private int x;
	private int y;

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

	@Override
	public String toString() {
		return code + "    " + descrlption + "     " + packing + "      "
				+ unit + "      " + qty + "       " + unitPrice + "       "
				+ amount + "       " + others;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescrlption() {
		return descrlption;
	}

	public void setDescrlption(String descrlption) {
		this.descrlption = descrlption;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}
}
