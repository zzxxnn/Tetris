package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import config.ConfigFactory;
import config.GameConfig;
import dto.GameDto;

abstract public class Layer {
	
	//内边距

	protected static final int PADDINGF;
	//边框宽度
	protected static final int SIZE;

	protected static final int IMG_NUMBER_W = Img.NUMBER.getWidth(null) / 10;

	private static final int IMG_NUMBER_H = Img.NUMBER.getHeight(null);

	static {
		// 或的配置
		GameConfig cfg = ConfigFactory.getGmaeConfig();
		PADDINGF = cfg.getPadding();
		SIZE = cfg.getWindowSize();
	}

	private static int IMAGE_W = Img.WINDOW.getWidth(null);

	private static int IMAGE_H = Img.WINDOW.getHeight(null);

	protected static final int RECT_H = Img.RECT.getHeight(null);

	private static final int IMG_RECT_W = Img.RECT.getWidth(null);

	private static final Font A_FONT = new Font("黑体", Font.BOLD, 20);

	private int expW = this.w - (PADDINGF << 1);;
	// 窗口左上角x坐标
	protected int x;
	// 窗口左上角y坐标
	protected int y;
	// 窗口宽度
	protected int w;
	// 窗口高度
	protected int h;

	protected GameDto dto = null;

	public Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.expW = this.w - (PADDINGF << 1);
	}

	// 绘制窗口
	public void creatWindow(Graphics g) {
		// 【（1{起始显示位置}2）（3{终止显示位置}4）】【（5{起始切片位置}6）（7{终止切片位置}8）】
		g.drawImage(Img.WINDOW, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE,
				null);
		g.drawImage(Img.WINDOW, x + SIZE, y, x + w - SIZE, y + SIZE, SIZE, 0,
				IMAGE_W - SIZE, SIZE, null);
		g.drawImage(Img.WINDOW, x + w - SIZE, y, x + w, y + SIZE, IMAGE_W
				- SIZE, 0, IMAGE_W, SIZE, null);
		g.drawImage(Img.WINDOW, x, y + SIZE, x + SIZE, y + h - SIZE, 0, SIZE,
				SIZE, IMAGE_H - SIZE, null);
		g.drawImage(Img.WINDOW, x + SIZE, y + SIZE, x + w - SIZE, y + h - SIZE,
				SIZE, SIZE, IMAGE_W - SIZE, IMAGE_H - SIZE, null);
		g.drawImage(Img.WINDOW, x + w - SIZE, y + SIZE, x + w, y + h - SIZE,
				IMAGE_W - SIZE, SIZE, IMAGE_W, IMAGE_H - SIZE, null);
		g.drawImage(Img.WINDOW, x, y + h - SIZE, x + SIZE, y + h, 0, IMAGE_H
				- SIZE, SIZE, IMAGE_H, null);
		g.drawImage(Img.WINDOW, x + SIZE, y + h - SIZE, x + w - SIZE, y + h,
				SIZE, IMAGE_H - SIZE, IMAGE_W - SIZE, IMAGE_H, null);
		g.drawImage(Img.WINDOW, x + w - SIZE, y + h - SIZE, x + w, y + h,
				IMAGE_W - SIZE, IMAGE_H - SIZE, IMAGE_W, IMAGE_H, null);
	}

	abstract public void paint(Graphics g);

	public void setDto(GameDto dto) {
		this.dto = dto;
	}

	// 显示数字
	/**
	 * 
	 * @param x
	 *            //左上角x坐标
	 * @param y
	 *            //左上角y坐标
	 * @param num
	 *            //要现实的数字
	 * @param bitCont
	 *            //数字为数
	 * @param g
	 *            //画笔对象
	 */

	protected void drawNumberLeftPad(int x, int y, int num, int maxbit,
			Graphics g) {
		// 把数字num的每一位取出
		String strNum = Integer.toString(num);
		// 循环绘制右对齐
		for (int i = 0; i < maxbit; i++) {
			// 判断是否满足绘制条件
			if (maxbit - i <= strNum.length()) {
				// 获得数字在字符串中的下标
				int idx = i - maxbit + strNum.length();
				// 绘制数字
				int bit = strNum.charAt(idx) - '0';
				g.drawImage(Img.NUMBER, this.x + x + IMG_NUMBER_W * i, this.y
						+ y, this.x + x + IMG_NUMBER_W * (i + 1), this.y + y
						+ IMG_NUMBER_H, bit * IMG_NUMBER_W, 0, (bit + 1)
						* IMG_NUMBER_W, IMG_NUMBER_H, null);
			}
		}
	}

	// 绘制值曹

	protected void drawRcet(int expY, String title, String number,
			double p , Graphics g) {

		int rect_x = this.x + PADDINGF;
		int rect_y = this.y + expY;

		g.setColor(Color.black);

		g.fillRect(rect_x, rect_y, expW, RECT_H);

		g.setColor(Color.white);

		g.fillRect(rect_x + 2, rect_y + 2, expW - 4, RECT_H - 4);

		g.setColor(Color.black);

		g.fillRect(rect_x + 4, rect_y + 4, expW - 8, RECT_H - 8);
		// 绘制值曹
		// 求出宽度
		int w = (int) (p * (expW - 8));
		// 求出颜色
		int subIdx = (int) (p * IMG_RECT_W) - 1;

		g.drawImage(Img.RECT, rect_x + 4, rect_y + 4, rect_x + w + 4, rect_y
				+ RECT_H - 4, subIdx, 0, subIdx + 1, RECT_H, null);
		g.setColor(Color.white);
		g.setFont(A_FONT);
		g.drawString(title, rect_x + 4, rect_y + 22);
		if (number != null) {
			// TODO
			g.drawString(number, rect_x + 250, rect_y + 22);
		}
	}

	// 正中绘图
	protected void drawImageAtCenter(Image img, Graphics g) {
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		int imgX = this.w - imgW >> 1;
		int imgY = this.h - imgH >> 1;
		g.drawImage(img, this.x + imgX, this.y + imgY, null);
	}
}
