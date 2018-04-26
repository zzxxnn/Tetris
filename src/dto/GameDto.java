package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.GameFunction;
import entity.GameAct;

public class GameDto {
	// 数据库记录
	private List<Player> dbRecode;
	// 本地记录
	private List<Player> diskRecode;
	// 游戏地图
	private boolean[][] gameMap;
	// 下落方块
	private GameAct gameAct;
	// 下一个方块
	private int next;
	// 等级
	private int nowLevel;
	// 等级
	private int nowPoint;
	// 消行
	private int nowRemoveLine;
	
	//游戏是否是开始状态
	private boolean start;
	
	//阴影
	private boolean showShadow;
	//暂停
	private boolean pause;
	//是否使用作弊
	private Boolean cheat = false;
	//县城等待时间
	private long sleepTime;
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	// 构造函数
	public GameDto() {
		dtoInit();
	}

	public void dtoInit() {
		// TODO 硬编码
		this.gameMap = new boolean[10][18];
		// TODO
		this.nowLevel = 0;
		this.nowPoint = 0;
		this.nowRemoveLine = 0;
		this.pause = false;
		this.cheat = false;
		this.sleepTime =  GameFunction.getSleepTimeLevel(this.nowLevel);
	}

	public List<Player> getDbRecode() {
		return dbRecode;
	}

	public void setDbRecode(List<Player> dbRecode) {

		this.dbRecode = this.setFillRecode(dbRecode);
	}

	public List<Player> getDiskRecode() {
		return diskRecode;
	}

	public void setDiskRecode(List<Player> diskRecode) {

		this.diskRecode = this.setFillRecode(diskRecode);
	}
	//设置数据并排序
	private List<Player> setFillRecode(List<Player> players) {
		if (players == null) {
			players = new ArrayList<Player>();
		}
		while (players.size() < 5) {
			players.add(new Player("No Data", 0));
		}
		Collections.sort(players);
		return players;
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getNowLevel() {
		return nowLevel;
	}

	public void setNowLevel(int nowLevel) {
		this.nowLevel = nowLevel;
		//计算线程睡眠时间(下落速度)
		this.sleepTime = GameFunction.getSleepTimeLevel(this.nowLevel);
	}

	public int getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}

	public int getNowRemoveLine() {
		return nowRemoveLine;
	}

	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}

	public boolean isShowShadow() {
		return showShadow;
	}

	public void changeShowShadow() {
		this.showShadow = !this.showShadow;
	}

	public boolean isPause() {
		return pause;
	}

	public void changePause() {
			this.pause =!this.pause;
		
		
	}

	public Boolean getCheat() {
		return cheat;
	}

	public void setCheat(Boolean cheat) {
		this.cheat = cheat;
	}

	public long getSleepTime() {
		return sleepTime;
	}
}
