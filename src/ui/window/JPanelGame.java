package ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Img;
import ui.Layer;
import config.ConfigFactory;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

@SuppressWarnings("serial")
public class JPanelGame extends JPanel {
	
	
	private JButton jbStart;
	
	private JButton jbSet;
	
	private GameControl gameControl = null;
	
	

	private List<Layer> layers = null;


	public JPanelGame(GameControl gameControl,GameDto dto) {
		//链接游戏控制器
		this.gameControl = gameControl;
		//设置布局管理器
		this.setLayout(null);
		// 初始化组建
		this.iniComponent();
		// 初始化层
		this.initLayer(dto);
		//安装键盘监听器
		this.setGameControl(new PlayerControl(gameControl));

	}

	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}

	private void iniComponent() {
		//初始化开始按钮
		this.jbStart = new JButton(Img.START);
		jbStart.setBounds(820, 74, 105, 40);
		this.jbStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}
		});
		this.add(jbStart);
		//初始化设置按钮
		this.jbSet = new JButton(Img.CONFIG);
		jbSet.setBounds(965, 74, 105, 40);
		this.jbSet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		this.add(jbSet);
	}
	/**
	 * 显示玩家控制窗口
	 */


	private void initLayer(GameDto dto) {
		try {
			// 获取游戏配置
			GameConfig cfg = ConfigFactory.getGmaeConfig();
			// 获得层配置

			List<LayerConfig> layerscfg = cfg.getLayersConfig();

			// 创建游戏层数组
			layers = new ArrayList<Layer>(layerscfg.size());

			// 创建所有层对象

			for (LayerConfig asdfg : layerscfg) {
				// 获得类对象
				Class<?> c;
				c = Class.forName(asdfg.getClassName());
				// 获得构造函数
				Constructor ctr = c.getConstructor(int.class, int.class,
						int.class, int.class);
				// 调用构造函数创建对象
				Layer l = (Layer) ctr.newInstance(asdfg.getX(), asdfg.getY(),
						asdfg.getW(), asdfg.getH());
				// 设置游戏数据对象
				l.setDto(dto);
				// 把創建的layer對象的集合
				layers.add(l);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// lays = new Layer[]{
	// //硬编码 非常不好的开发习惯
	// //我们要尽量将数字或字符串定义成常量，或者写入配置文件
	// new LayerBackground(0,0,1162,660),
	// new LayerDateBase(40,32,334,279),
	// new LayerDisk(40,343,334,279),
	// new LayerGame(414,32,334,590),
	// new LayerButton(788,32,334,124),
	// new LayerNext(788,188,176,148),
	// new LayerLevel(964,188,158,148),
	// new LayerPoint(788,368,334,200)
	//
	// };

	@Override
	public void paintComponent(Graphics g) {
		// 调用基类方法
		super.paintComponent(g);
		// 循环刷新游戏画面
		for (int i = 0; i < layers.size(); layers.get(i++).paint(g));
		// 刷新曾窗口
		this.requestFocus();
	}
	public void buttonSwitch(boolean on){
		this.jbSet.setEnabled(on);
		this.jbStart.setEnabled(on);
		
		
	}
	
}
