package ui;

import java.awt.Graphics;

public class LayerLevel extends Layer {

	private static int IMG_LEVEL_W = Img.LEVEL.getWidth(null);

	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.creatWindow(g);
		// 窗口标题
		int centerX = this.x + (IMG_LEVEL_W >> 1);

		g.drawImage(Img.LEVEL, this.x + PADDINGF, this.y + PADDINGF, null);
		// 显示等级
		this.drawNumberLeftPad(64, 64, this.dto.getNowLevel(), 2, g);
	}

}