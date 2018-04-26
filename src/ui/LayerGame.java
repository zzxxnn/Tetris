package ui;

import java.awt.Graphics;
import java.awt.Point;

import entity.GameAct;

public class LayerGame extends Layer {

	// TODO �����ļ�

	private static final int SIZE_ROL = 5;

	private static final int LEFT_SIDE = 0;

	private static final int RIGHT_SIDE = 8;
	
	private static final int LOSE_INDEX = 7;

	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.creatWindow(g);
		// ��÷������
			// ��÷������鼯��
			GameAct act = this.dto.getGameAct();
			if(act != null){
				Point[] points = act.getActPoints();
			// ������Ӱ
			this.drawShadow(points, g);
			// ���ƻ����
			this.drawMainAct(points, g);
			}
			

		// ���Ƶ�ͼ
		this.drawMap(g);
		//��ͣ
		if(this.dto.isPause()){
			this.drawImageAtCenter(Img.PAUSE, g);
			
		}
	}

	private void drawMap(Graphics g) {
		// ���Ƶ�ͼ
		boolean[][] map = this.dto.getGameMap();
		// ����ͼƬ��ɫ
		int lv = this.dto.getNowLevel();

		int imgIdx = lv == 0 ? 0 : (lv - 1) % 7 + 1;
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y]) {
					this.drawActByPoint(x, y, imgIdx, g);

				}

			}
		}
	}

	private void drawMainAct(Point[] points, Graphics g) {

		// ��÷������ͱ��(0-6)
		int typeCode = this.dto.getGameAct().gettypeCode();
		// ��ӡ����
		for (int i = 0; i < points.length; i++) {
			this.drawActByPoint(points[i].x, points[i].y, typeCode + 1, g);
			// g.drawImage(ACT,
			// this.x + (points[i].x << SIZE_ROL ) + 7,
			// this.y + (points[i].y << SIZE_ROL ) + 7,
			// this.x + ( points[i].x + 1 << SIZE_ROL) + 7,
			// this.y + ( points[i].y + 1 << SIZE_ROL) + 7,
			// (typeCode + 1) << SIZE_ROL, 0, (typeCode + 2) << SIZE_ROL, 1 <<
			// SIZE_ROL, null);
		}
	}

	private void drawShadow(Point[] points, Graphics g) {
		
		if (!this.dto.isShowShadow()) {
			return;
		}
		int leftX = RIGHT_SIDE;
		int rightX = LEFT_SIDE;
		for (Point p : points) {
			leftX = p.x < leftX ? p.x : leftX;
			rightX = p.x > rightX ? p.x : rightX;

		}

		g.drawImage(Img.SHADOW, this.x + SIZE + (leftX << SIZE_ROL), this.y
				+ SIZE, (rightX - leftX + 1) << SIZE_ROL, this.h - (SIZE << 1),
				null);
	}

	private void drawActByPoint(int x, int y, int imgIdx, Graphics g1) {
		imgIdx = this.dto.isStart() ? imgIdx : LOSE_INDEX;
		g1.drawImage(Img.ACT, this.x + (x << SIZE_ROL) + 7, this.y
				+ (y << SIZE_ROL) + 7, this.x + (x + 1 << SIZE_ROL) + 7, this.y
				+ (y + 1 << SIZE_ROL) + 7, (imgIdx + 1) << SIZE_ROL, 0,
				(imgIdx + 2) << SIZE_ROL, 1 << SIZE_ROL, null);
	}

}
