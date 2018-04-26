package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import config.ConfigFactory;
import config.GameConfig;
import dto.GameDto;

abstract public class Layer {
	
	//�ڱ߾�

	protected static final int PADDINGF;
	//�߿���
	protected static final int SIZE;

	protected static final int IMG_NUMBER_W = Img.NUMBER.getWidth(null) / 10;

	private static final int IMG_NUMBER_H = Img.NUMBER.getHeight(null);

	static {
		// �������
		GameConfig cfg = ConfigFactory.getGmaeConfig();
		PADDINGF = cfg.getPadding();
		SIZE = cfg.getWindowSize();
	}

	private static int IMAGE_W = Img.WINDOW.getWidth(null);

	private static int IMAGE_H = Img.WINDOW.getHeight(null);

	protected static final int RECT_H = Img.RECT.getHeight(null);

	private static final int IMG_RECT_W = Img.RECT.getWidth(null);

	private static final Font A_FONT = new Font("����", Font.BOLD, 20);

	private int expW = this.w - (PADDINGF << 1);;
	// �������Ͻ�x����
	protected int x;
	// �������Ͻ�y����
	protected int y;
	// ���ڿ��
	protected int w;
	// ���ڸ߶�
	protected int h;

	protected GameDto dto = null;

	public Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.expW = this.w - (PADDINGF << 1);
	}

	// ���ƴ���
	public void creatWindow(Graphics g) {
		// ����1{��ʼ��ʾλ��}2����3{��ֹ��ʾλ��}4��������5{��ʼ��Ƭλ��}6����7{��ֹ��Ƭλ��}8����
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

	// ��ʾ����
	/**
	 * 
	 * @param x
	 *            //���Ͻ�x����
	 * @param y
	 *            //���Ͻ�y����
	 * @param num
	 *            //Ҫ��ʵ������
	 * @param bitCont
	 *            //����Ϊ��
	 * @param g
	 *            //���ʶ���
	 */

	protected void drawNumberLeftPad(int x, int y, int num, int maxbit,
			Graphics g) {
		// ������num��ÿһλȡ��
		String strNum = Integer.toString(num);
		// ѭ�������Ҷ���
		for (int i = 0; i < maxbit; i++) {
			// �ж��Ƿ������������
			if (maxbit - i <= strNum.length()) {
				// ����������ַ����е��±�
				int idx = i - maxbit + strNum.length();
				// ��������
				int bit = strNum.charAt(idx) - '0';
				g.drawImage(Img.NUMBER, this.x + x + IMG_NUMBER_W * i, this.y
						+ y, this.x + x + IMG_NUMBER_W * (i + 1), this.y + y
						+ IMG_NUMBER_H, bit * IMG_NUMBER_W, 0, (bit + 1)
						* IMG_NUMBER_W, IMG_NUMBER_H, null);
			}
		}
	}

	// ����ֵ��

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
		// ����ֵ��
		// ������
		int w = (int) (p * (expW - 8));
		// �����ɫ
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

	// ���л�ͼ
	protected void drawImageAtCenter(Image img, Graphics g) {
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		int imgX = this.w - imgW >> 1;
		int imgY = this.h - imgH >> 1;
		g.drawImage(img, this.x + imgX, this.y + imgY, null);
	}
}
