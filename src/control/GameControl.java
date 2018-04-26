package control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import service.GameService;
import service.GameTetris;
import ui.window.JFrameConfig;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;
import dao.Data;
import dao.DataBase;
import dao.DataDisk;
import dto.GameDto;
import dto.Player;

public class GameControl {
	
	/**
	 * 创建游戏数据源
	 */
	private GameDto dto = null;
	

	// 数据访问接口A

	private Data dataA;
	// 数据访问接口B

	private Data dataB;

	// 游戏界面层
	private JPanelGame panelGame;
	// 游戏逻辑层
	private GameService gameService;

	private Map<Integer, Method> action;
	// 游戏控制设置窗口
	private JFrameConfig frameConfig;
	
	//保存分数窗口
	private JFrameSavePoint savePoint;
	
	private final static String LUJING = "data/control.dat";
	
	// 游戏线程
	private Thread gameThread = null;
	
	public GameControl() {
		// 创建游戏数据源
		this.dto = new GameDto();
		// 创建游戏逻辑模块（安装游戏数据源）
		this.gameService =new GameTetris(dto);

//		获取本地记录
		this.dataB = new DataDisk();
		this.dto.setDiskRecode(dataB.loadData());
		
		//		获取数据库记录
		this.dataA = new DataBase();
		this.dto.setDbRecode(dataA.loadData());
		
		//创建游戏面板
		this.panelGame = new JPanelGame(this,dto);
		this.setControlConfig();
		// 初始化用户配置窗口
		this.frameConfig = new JFrameConfig(this);
		//初始化保存分数窗口
		this.savePoint = new JFrameSavePoint(this);
		//创建游戏窗口
		new JFrameGame(this.panelGame);
		
		// try {
		// action.put(37,this.gameService.getClass().getMethod("keyLeft"));
		// action.put(38,this.gameService.getClass().getMethod("keyUp"));
		// action.put(39,this.gameService.getClass().getMethod("keyRight"));
		// action.put(40,this.gameService.getClass().getMethod("keyDown"));
		// action.put(49,this.gameService.getClass().getMethod("testLevelUp"));
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 读取用户设置
	 */
	public void setControlConfig() {
		this.action = new HashMap<Integer, Method>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					LUJING));
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>) ois
					.readObject();
			ois.close();
			Set<Entry<Integer, String>> entryset = cfgSet.entrySet();
			for (Entry<Integer, String> e : entryset) {
				action.put(e.getKey(),
						this.gameService.getClass().getMethod(e.getValue()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actionByKeyCode(int keyCode) {
		try {
			if (this.action.containsKey(keyCode)) {
				this.action.get(keyCode).invoke(this.gameService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}

	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}

	// 子窗口关闭时间
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}

	// 开始按钮时间
	public void start() {
		//面板按钮设置为不可点击
		this.panelGame.buttonSwitch(false);
		//关闭窗口
		this.panelGame.buttonSwitch(false);
		//
		this.savePoint.setVisible(false);
		
		
		//游戏数据初始化
		this.gameService.startGame();

		//创建线程对象
		this.gameThread = new mainThread();
		//启动
		this.gameThread.start();
		this.panelGame.repaint();
	}
	/**
	 * 失败之后的处理
	 */
	public void afterLose(){
		if(!this.dto.getCheat()){
			//显示保存得分窗口
		this.savePoint.show(this.dto.getNowPoint());
		}
		//使按钮可以点击
		this.panelGame.buttonSwitch(true);
		
	}
	private class mainThread extends Thread{
		@Override
		public void run(){
			//刷新画面
			panelGame.repaint();
			//主循环
			while (dto.isStart()) {
				try {
					//县城睡眠时间
					Thread.sleep(dto.getSleepTime());
					//如果暂停不执行主行为
					if(dto.isPause()){
						continue;
					}
					//方块下落
					gameService.mainAction();
					//刷新画面
					panelGame.repaint();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			afterLose();
		}
	}
	//保存分数
	public void savePoint(String string) {
		Player players = new Player(string, this.dto.getNowPoint());
		//保存记录到数据库和磁盘
		this.dataB.saveData(players);
//		this.dataA.saveData(players);
		this.dto.setDbRecode(dataA.loadData());
		this.dto.setDiskRecode(dataB.loadData());
		this.panelGame.repaint();
	}
}
