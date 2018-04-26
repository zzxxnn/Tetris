package ui;

import java.awt.Graphics;

public class LayerNext extends Layer {

	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.creatWindow(g);
		if(this.dto.isStart()){
			this.drawImageAtCenter(Img.NEXT_ACT[this.dto.getNext()], g);

		}
	}
}
