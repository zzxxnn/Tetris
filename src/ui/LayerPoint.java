package ui;

import java.awt.Graphics;

public class LayerPoint extends Layer {

	private static int POINT_Y;

	private static final int LEVEL_UP = 20;

	private static int IMG_RMLINE_H;

	private static final int POINT_BIT = PADDINGF;

	private final int comX;

	private final int expY;

	public LayerPoint(int x, int y, int w, int h) {

		super(x, y, w, h);
		// ��ʼ����ͬx����
		comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDINGF;
		// ��ʼ��������ʾY����
		POINT_Y = PADDINGF;
		// ��ʼ��������ʾY����
		IMG_RMLINE_H = LayerPoint.POINT_Y + Img.POINT.getHeight(null)
				+ (PADDINGF);
		// ����ֵY����
		this.expY = LayerPoint.IMG_RMLINE_H + Img.RMLINE.getHeight(null)
				+ PADDINGF;
		// ��ʼ��ֵ�ܿ��

	}

	@Override
	public void paint(Graphics g) {
		this.creatWindow(g);
		// ���ڱ���
		g.drawImage(Img.POINT, this.x + PADDINGF, this.y + PADDINGF, null);
		this.drawNumberLeftPad(comX, POINT_Y, this.dto.getNowPoint(),
				POINT_BIT, g);

		// ���ڱ���
		g.drawImage(Img.RMLINE, this.x + PADDINGF, this.y + IMG_RMLINE_H, null);

		this.drawNumberLeftPad(comX, IMG_RMLINE_H, this.dto.getNowRemoveLine(),
				POINT_BIT, g);
		// ����ֵ��
		int rmline = this.dto.getNowRemoveLine();
		
		this.drawRcet(expY, "��һ��", null, (double)(rmline % LEVEL_UP)/
				 (double)LEVEL_UP, g);

	}

}
