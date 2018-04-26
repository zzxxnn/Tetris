package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import dto.Player;

abstract public class LayerData extends Layer{
	private static final int MAX_ROW = 5;
	//起始y坐标
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
		//绘制标题
				g.drawImage(title,this.x+PADDINGF , this.y+PADDINGF,null);
				//获得数据对象
				//获取现在分数
				int nowPoint = this.dto.getNowPoint();
				//循环绘制记录
				for (int i = 0; i < MAX_ROW; i++) {
					//获得一条完假记录
					Player player = players.get(i);
					//获得该玩家分数
					int recodePoint = player.getPoint();
					//计算现在分数与记录的比值
					double percent = (double)nowPoint/player.getPoint();
					//如果记录被打破 比值设为1
					percent = percent>1?1.0:percent;
					//绘制单条记录
					String asd = recodePoint ==0 ? null : Integer.toString(recodePoint);
					this.drawRcet(START_Y + i*(aRECT_H + SPA), player.getName(),asd ,percent, g);
				}
	}
	

}
