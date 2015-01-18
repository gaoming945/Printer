package com.ufgov;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ufgov.prop.ParseConfigXML;
import com.ufgov.prop.ParsePositionXML;
import com.ufgov.prop.Props;
import com.ufgov.util.MergsBeanUtil;
import com.ufgov.vo.Config;
import com.ufgov.vo.Product;

public class Printer implements Printable {

	private static final Logger logger = LoggerFactory.getLogger(Printer.class);

	private int pageSize;// 打印的总页数
	private double paperW = 0;// 打印的纸张宽度
	private double paperH = 0;// 打印的纸张高度

	// 实现java.awt.print.Printable接口的打印方法
	// pageIndex:打印的当前页，此参数是系统自动维护的，不需要手动维护，系统会自动递增
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex >= pageSize)
			// 退出打印
			return Printable.NO_SUCH_PAGE;
		else {
			Graphics2D g2 = (Graphics2D) graphics;
			g2.setColor(Color.BLUE);
			Paper p = new Paper();
			// 此处的paperW和paperH是从目标打印机的进纸规格中获取的，实际针式打印机的可打印区域是有限的，
			// 距纸张的上下左右1inch(英寸)的中间的距形框为实际可打印区域，超出范围的内容将不会打印出来(没有设置偏移的情况)
			// 如果设置偏移量，那么超出的范围也是可以打印的，这里的pageW和pageH我是直接获取打印机的进纸规格的宽和高
			// 也可以手动指定，从是如果手动指定的宽高和目标打印机的进纸规格相差较大，将会默认以A4纸为打印模版
			p.setImageableArea(0, 0, paperW, paperH);// 设置可打印区域
			p.setSize(paperW, paperH);// 设置纸张的大小
			pageFormat.setPaper(p);
			try {
				drawCurrentPageText(g2, pageFormat);
			} catch (Exception e) {
				e.printStackTrace();
			}// 调用打印内容的方法
			return PAGE_EXISTS;
		}
	}

	// 打印内容
	@SuppressWarnings("unchecked")
	private void drawCurrentPageText(Graphics2D g2, PageFormat pf)
			throws Exception {
		Font font = new Font("新宋体", Font.BOLD, 11);
		// 设置打印的字体
		g2.setFont(font);// 设置字体
		// 此处打印一句话，打印开始位置是(200,200),表示从pf.getPaper()中座标为(200,200)开始打印
		// 此处200的单位是1/72(inch)，inch:英寸，所以这里的长度，在测量后需要进行转换

		Object[] configs = ParseConfigXML.ReadXml(Props.getConfig());
		Object[] products = ParsePositionXML.ReadXml(Props.getPosition());

		Map<String, Config> config1 = (Map<String, Config>) configs[0];
		Map<String, Config> config2 = (Map<String, Config>) products[0];

		logger.debug("【开始】解析config.xml和position.xml配置文件成功，开始构建对象并打印config信息");
		Iterator<String> keys = config1.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			MergsBeanUtil.CopyBeanToBean(config2.get(key), config1.get(key));
			g2.drawString(config1.get(key).getTitle(), config1.get(key).getX(),
					config1.get(key).getY());
		}
		logger.debug("【结束】打印config信息结束");
		// --------------------------

		Map<String, Product> postion1 = (Map<String, Product>) configs[1];
		Map<String, Product> postion2 = (Map<String, Product>) products[1];
		Iterator<String> keys1 = postion1.keySet().iterator();
		int i = 1;

		logger.debug("【开始】解析config.xml和position.xml配置文件成功，开始构建对象并打印product信息");
		while (keys1.hasNext()) {
			String key = keys1.next();
			Product product = postion1.get(key);

			product.setX(postion2.get("product_code").getX());
			product.setY(100 + postion2.get("product_code").getY() * i);

			g2.drawString(product.getCode() + "  " + product.getDescrlption()
					+ "  " + product.getPacking() + "  " + product.getUnit()
					+ "  " + product.getQty() + "  " + product.getUnitPrice()
					+ "  " + product.getAmount() + "  " + product.getOthers(),
					product.getX(), product.getY());

			i++;
		}
		logger.debug("【结束】打印product信息结束");

	}

	// 连接打印机，弹出打印对话框
	public void starPrint() {
		logger.info(new Date() + "进入打印程序");
		try {
			PrinterJob prnJob = PrinterJob.getPrinterJob();
			PageFormat pageFormat = new PageFormat();
			pageFormat.setOrientation(PageFormat.PORTRAIT);
			prnJob.setPrintable(this);
			// 弹出打印对话框，也可以选择不弹出打印提示框，直接打印
			if (!prnJob.printDialog())
				return;
			// 获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)
			paperW = prnJob.getPageFormat(null).getPaper().getWidth();
			// 获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)
			paperH = prnJob.getPageFormat(null).getPaper().getHeight();
			logger.info("now {}", "开始执行打印");
			prnJob.print();// 启动打印工作
			logger.info("now {}", "结束执行打印");
		} catch (PrinterException ex) {
			logger.error("打印机异常-->" + ex.toString());
			ex.printStackTrace();
		}
	}

	// 入口方法
	public static void main(String[] args) {
		Printer pm = new Printer();// 实例化打印类
		pm.pageSize = 1;// 打印1页
		pm.starPrint();
	}
}
