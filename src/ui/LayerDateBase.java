package ui;

import java.awt.Graphics;

public class LayerDateBase extends LayerData {

	public LayerDateBase(int x, int y, int w, int h) {
		super(x, y, w, h);

	}

	@Override
	public void paint(Graphics g) {
		this.creatWindow(g);
		this.showData(Img.DB,this.dto.getDbRecode(), g);

	}
}
