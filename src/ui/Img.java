package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Img {
	private Img() {
	}
	public static ImageIcon START = new ImageIcon("mulu/string/strat.png");
	
	public static Image PAUSE = new ImageIcon("mulu/string/pause.png").getImage();


	public static ImageIcon CONFIG = new ImageIcon("mulu/string/config.png");

	
	public static Image SIGN = new ImageIcon("mulu/string/sign.png").getImage();
	public static Image WINDOW = new ImageIcon("mulu/window/555.png")
			.getImage();
	public static Image NUMBER = new ImageIcon("mulu/string/num.png")
			.getImage();
	public static Image RECT = new ImageIcon("mulu/window/rect.png").getImage();
	public static Image DB = new ImageIcon("mulu/string/db.png").getImage();
	public static Image DISK = new ImageIcon("mulu/string/disk.png").getImage();
	public static Image ACT = new ImageIcon("mulu/game/rect.jpg").getImage();
	public static Image SHADOW = new ImageIcon("mulu/game/shodow.png").getImage();

	public static Image LEVEL = new ImageIcon("mulu/string/level.png")
			.getImage();

	public static Image POINT = new ImageIcon("mulu/string/point.png")
			.getImage();
	public static Image RMLINE = new ImageIcon("mulu/string/rmline.png")
			.getImage();
	//下一个
	public static Image[] NEXT_ACT;
	public static List<Image> BG_LIST;

	static {
		NEXT_ACT = new Image[7];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon("mulu/game/" + i + ".png").getImage();
		}
		//背景图片数组
		File dic = new File("mulu/background");
		File[] files = dic.listFiles();
		BG_LIST = new ArrayList<Image>();
		for(File file:files){
			if(file.isDirectory()){
				continue;
			}
			BG_LIST.add(new ImageIcon(file.getPath()).getImage());
			
		}
	}
	

}
