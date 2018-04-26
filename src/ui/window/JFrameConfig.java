package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import util.FrameUtil;
import control.GameControl;

public class JFrameConfig extends JFrame {

	private JButton jbOk = new JButton("确定");

	private JButton jbCancel = new JButton("取消");

	private JButton jbUser = new JButton("应用");

	private GameControl gamecontrol;

	private final static Image IMAGE_PSP = new ImageIcon("data/psp.png")
			.getImage();

	private final static String[] METHOD_NAMES = { "keyRight", "keyUp",
			"keyLeft", "keyDown", "keyFunLeft", "keyFunUp", "keyFunRight",
			"keyFunDown" };

	private JLabel errorMsg = new JLabel();

	private final static String LUJING = "data/control.dat";

	private TextCtrl[] testField = new TextCtrl[8];
	
	@SuppressWarnings("rawtypes")
	private JList skinList = null;
	
	@SuppressWarnings("rawtypes")
	private DefaultListModel skinData = new DefaultListModel();
	
	private JPanel skinView = null;

	public JFrameConfig(GameControl gamecontrol) {
		// 获得游戏控制器对象
		this.gamecontrol = gamecontrol;

		this.setTitle("按键设置....");
		// 设置为边界布局
		this.setLayout(new BorderLayout());
		// 初始化按键输入框
		this.inikettext();
		// 添加主面板

		this.add(this.creatMainPannel(), BorderLayout.CENTER);
		// 添加按钮
		this.add(this.creatButtonPanel(), BorderLayout.SOUTH);

		/**
		 * TODO 测试用
		 */

		this.setSize(780, 480);
		// this.setDefaultCloseOperation(3);
		// this.setVisible(true);
		FrameUtil.setFrameCenter(this);
	}

	/**
	 * 初始化按键输入框
	 */
	private void inikettext() {
		int x = 0;
		int y = 130;
		int w = 64;
		int h = 20;
		for (int i = 0; i < 4; i++) {
			testField[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 25;
		}
		x = 690;
		y = 130;
		for (int i = 4; i < 8; i++) {
			testField[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 25;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					LUJING));
			HashMap<Integer, String> cfgSet = (HashMap) ois.readObject();
			ois.close();
			Set<java.util.Map.Entry<Integer, String>> entrySet = cfgSet
					.entrySet();
			for (java.util.Map.Entry<Integer, String> e : entrySet) {
				// System.out.println(e.getKey()+ e.getValue());
				for (TextCtrl hh : testField) {
					if (hh.getMethodName().equals(e.getValue())) {
						hh.setKeyCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建按钮面板
	 * 
	 * @return
	 */

	private Component creatButtonPanel() {
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.errorMsg.setForeground(Color.red);

		jPanel.add(this.errorMsg);
		// 给确定按钮增加事件监听
		this.jbOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okButtonConfig();
			}
		});
		jPanel.add(this.jbOk);
		// 给取消按钮增加事件监听
		this.jbCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelButtonConfig();
			}

		});
		jPanel.add(this.jbCancel);
		// 给使用按钮增加事件监听
		this.jbUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		});
		jPanel.add(this.jbUser);
		return jPanel;
	}

	/**
	 * 创建主面板（选项卡面板）
	 * 
	 * @return
	 */

	private Component creatMainPannel() {
		JTabbedPane jTabbedPane = new JTabbedPane();
		jTabbedPane.addTab("控制设置", this.createControlPannel());
		jTabbedPane.addTab("皮肤设置", this.creatSkinPanel());
		return jTabbedPane;
	}
	/**
	 * 玩家皮肤面板
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Component creatSkinPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		this.skinData.addElement("11111");
		this.skinData.addElement("hehe");
		this.skinData.addElement("haha");
		this.skinData.addElement("xixi");
		this.skinData.addElement("caocao");
		this.skinList = new JList(this.skinData);
		
		this.skinView = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(null, 0, 0,  null);
			}
		};
		
		panel.add(this.skinList,BorderLayout.WEST);
		
		panel.add(this.skinView,BorderLayout.CENTER);
		
		
		return panel;
	}

	private Component createControlPannel() {
		JPanel jPane = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(IMAGE_PSP, 0, 0, null);
			}
		};
		// 设置布局管理器
		jPane.setLayout(null);
		for (int i = 0; i < testField.length; i++) {
			jPane.add(testField[i]);
		}

		return jPane;
	}

	private void okButtonConfig() {
		this.writeConfig();
		if (writeConfig()) {
			this.setVisible(false);
			gamecontrol.setOver();
		}
	}

	private void cancelButtonConfig() {
		this.setVisible(false);
		gamecontrol.setOver();
	}

	private boolean writeConfig() {
		HashMap<Integer, String> keySet = new HashMap<Integer, String>();
		for (int i = 0; i < this.testField.length; i++) {
			int keycode = this.testField[i].getKeyCode();
			if (keycode == 0) {
				this.errorMsg.setText("请先设置按键");
				return false;
			}
			keySet.put(keycode, this.testField[i].getMethodName());
		}
		if (keySet.size() != 8) {
			this.errorMsg.setText("请不要设置相同的按键...");
			return false;

		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(LUJING));
			oos.writeObject(keySet);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMsg.setText(e.getMessage());

			return false;
		}
		this.errorMsg.setText(null);
		return true;
	}
	// 子窗口关闭时间

	// public static void main(String[] args) {
	// new FrameConfig();
	// }
}
