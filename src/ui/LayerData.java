package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import dto.Player;

abstract public class LayerData extends Layer{
	private static final int MAX_ROW = 5;
	//��ʼy����
	private static int START_Y = 0;
	
	private static int aRECT_H = RECT_H + 4;
	
	private static int SPA = 0;

	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA = (this.h - aRECT_H*5-(PADDINGF<<1) - Img.DB.getHeight(null))/MAX_ROW;
		START_Y = PADDINGF + Img.DB.getHeight(null)+SPA;
	}

	@Override
	abstract public void paint(Graphics g); 
	
	
	public void showData(Image title,List<Player> players,Graphics g){
		//���Ʊ���
				g.drawImage(title,this.x+PADDINGF , this.y+PADDINGF,null);
				//������ݶ���
				//��ȡ���ڷ���
				int nowPoint = this.dto.getNowPoint();
				//ѭ�����Ƽ�¼
				for (int i = 0; i < MAX_ROW; i++) {
					//���һ����ټ�¼
					Player player = players.get(i);
					//��ø���ҷ���
					int recodePoint = player.getPoint();
					//�������ڷ������¼�ı�ֵ
					double percent = (double)nowPoint/player.getPoint();
					//�����¼������ ��ֵ��Ϊ1
					percent = percent>1?1.0:percent;
					//���Ƶ�����¼
					String asd = recodePoint ==0 ? null : Integer.toString(recodePoint);
					this.drawRcet(START_Y + i*(aRECT_H + SPA), player.getName(),asd ,percent, g);
				}
	}
	

}
