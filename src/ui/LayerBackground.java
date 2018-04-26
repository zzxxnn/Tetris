package ui;

import java.awt.Graphics;

public class LayerBackground extends Layer {
	public LayerBackground(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		int bgindex = this.dto.getNowLevel()%Img.BG_LIST.size();
		g.drawImage(Img.BG_LIST.get(bgindex), 0, 0,1162,660, null);
	}

}
