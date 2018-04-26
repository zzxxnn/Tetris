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
		// 初始化共同x坐标
		comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDINGF;
		// 初始化分数显示Y坐标
		POINT_Y = PADDINGF;
		// 初始化消行显示Y坐标
		IMG_RMLINE_H = LayerPoint.POINT_Y + Img.POINT.getHeight(null)
				+ (PADDINGF);
		// 经验值Y坐标
		this.expY = LayerPoint.IMG_RMLINE_H + Img.RMLINE.getHeight(null)
				+ PADDINGF;
		// 初始化值曹宽度

	}

	@Override
	public void paint(Graphics g) {
		this.creatWindow(g);
		// 窗口标题
		g.drawImage(Img.POINT, this.x + PADDINGF, this.y + PADDINGF, null);
		this.drawNumberLeftPad(comX, POINT_Y, this.dto.getNowPoint(),
				POINT_BIT, g);

		// 窗口标题
		g.drawImage(Img.RMLINE, this.x + PADDINGF, this.y + IMG_RMLINE_H, null);

		this.drawNumberLeftPad(comX, IMG_RMLINE_H, this.dto.getNowRemoveLine(),
				POINT_BIT, g);
		// 经验值曹
		int rmline = this.dto.getNowRemoveLine();
		
		this.drawRcet(expY, "下一级", null, (double)(rmline % LEVEL_UP)/
				 (double)LEVEL_UP, g);

	}

}
