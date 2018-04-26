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
	 * ������Ϸ����Դ
	 */
	private GameDto dto = null;
	

	// ���ݷ��ʽӿ�A

	private Data dataA;
	// ���ݷ��ʽӿ�B

	private Data dataB;

	// ��Ϸ�����
	private JPanelGame panelGame;
	// ��Ϸ�߼���
	private GameService gameService;

	private Map<Integer, Method> action;
	// ��Ϸ�������ô���
	private JFrameConfig frameConfig;
	
	//�����������
	private JFrameSavePoint savePoint;
	
	private final static String LUJING = "data/control.dat";
	
	// ��Ϸ�߳�
	private Thread gameThread = null;
	
	public GameControl() {
		// ������Ϸ����Դ
		this.dto = new GameDto();
		// ������Ϸ�߼�ģ�飨��װ��Ϸ����Դ��
		this.gameService =new GameTetris(dto);

//		��ȡ���ؼ�¼
		this.dataB = new DataDisk();
		this.dto.setDiskRecode(dataB.loadData());
		
		//		��ȡ���ݿ��¼
		this.dataA = new DataBase();
		this.dto.setDbRecode(dataA.loadData());
		
		//������Ϸ���
		this.panelGame = new JPanelGame(this,dto);
		this.setControlConfig();
		// ��ʼ���û����ô���
		this.frameConfig = new JFrameConfig(this);
		//��ʼ�������������
		this.savePoint = new JFrameSavePoint(this);
		//������Ϸ����
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
	 * ��ȡ�û�����
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

	// �Ӵ��ڹر�ʱ��
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}

	// ��ʼ��ťʱ��
	public void start() {
		//��尴ť����Ϊ���ɵ��
		this.panelGame.buttonSwitch(false);
		//�رմ���
		this.panelGame.buttonSwitch(false);
		//
		this.savePoint.setVisible(false);
		
		
		//��Ϸ���ݳ�ʼ��
		this.gameService.startGame();

		//�����̶߳���
		this.gameThread = new mainThread();
		//����
		this.gameThread.start();
		this.panelGame.repaint();
	}
	/**
	 * ʧ��֮��Ĵ���
	 */
	public void afterLose(){
		if(!this.dto.getCheat()){
			//��ʾ����÷ִ���
		this.savePoint.show(this.dto.getNowPoint());
		}
		//ʹ��ť���Ե��
		this.panelGame.buttonSwitch(true);
		
	}
	private class mainThread extends Thread{
		@Override
		public void run(){
			//ˢ�»���
			panelGame.repaint();
			//��ѭ��
			while (dto.isStart()) {
				try {
					//�س�˯��ʱ��
					Thread.sleep(dto.getSleepTime());
					//�����ͣ��ִ������Ϊ
					if(dto.isPause()){
						continue;
					}
					//��������
					gameService.mainAction();
					//ˢ�»���
					panelGame.repaint();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			afterLose();
		}
	}
	//�������
	public void savePoint(String string) {
		Player players = new Player(string, this.dto.getNowPoint());
		//�����¼�����ݿ�ʹ���
		this.dataB.saveData(players);
//		this.dataA.saveData(players);
		this.dto.setDbRecode(dataA.loadData());
		this.dto.setDiskRecode(dataB.loadData());
		this.panelGame.repaint();
	}
}
