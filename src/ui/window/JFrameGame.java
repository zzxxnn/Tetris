package ui.window;

import javax.swing.JFrame;

import util.FrameUtil;
import config.ConfigFactory;
import config.GameConfig;

public class JFrameGame extends JFrame {

	
	
	public JFrameGame(JPanelGame panel) {

		
		// 获得游戏配置
		GameConfig cfg = ConfigFactory.getGmaeConfig();

		// 设置标题
		this.setTitle(cfg.getTitle());

		// 设置默认关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// shezhichaungkoudaxiao
		this.setSize(cfg.getWidth(), cfg.getHeight());
		// 设置不允许拖动
		this.setResizable(false);
		// 设置剧中 属性

		
		// 剧中
		FrameUtil.setFrameCenter(this);
		// 设计默认Panel
		this.setContentPane(panel);
		//默认显示窗口
		this.setVisible(true);
	}


}
