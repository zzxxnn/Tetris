package ui.window;

import javax.swing.JFrame;

import util.FrameUtil;
import config.ConfigFactory;
import config.GameConfig;

public class JFrameGame extends JFrame {

	
	
	public JFrameGame(JPanelGame panel) {

		
		// �����Ϸ����
		GameConfig cfg = ConfigFactory.getGmaeConfig();

		// ���ñ���
		this.setTitle(cfg.getTitle());

		// ����Ĭ�Ϲر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// shezhichaungkoudaxiao
		this.setSize(cfg.getWidth(), cfg.getHeight());
		// ���ò������϶�
		this.setResizable(false);
		// ���þ��� ����

		
		// ����
		FrameUtil.setFrameCenter(this);
		// ���Ĭ��Panel
		this.setContentPane(panel);
		//Ĭ����ʾ����
		this.setVisible(true);
	}


}
