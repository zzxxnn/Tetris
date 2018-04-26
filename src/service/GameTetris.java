package service;

import java.awt.Point;
import java.util.Random;

import dto.GameDto;
import entity.GameAct;

public class GameTetris implements GameService {

	private GameDto dto;
	// 随机数生成器
	private Random random = new Random();

	private static final int MAX_TYPE = 6;

	private static final int LEVEL_UP = 20;
	// 连续消行
	private static final int PLUS_POINT[] = { 10, 30, 60, 100 };

	public GameTetris(GameDto dto) {
		this.dto = dto;

	}

	// 旋转
	@Override
	public boolean keyUp() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().round(this.dto.getGameMap());
		}
		return true;
	}

	// 下面
	@Override
	public boolean keyDown() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized (this.dto) {
			// 方块向下移动并判定是否成功
			if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
				return false;
			}
			// 获得游戏地图对象
			boolean[][] map = this.dto.getGameMap();
			// 获得方块对象
			Point[] act = this.dto.getGameAct().getActPoints();
			// 将方块堆积到地图数组
			for (int i = 0; i < act.length; i++) {
				map[act[i].x][act[i].y] = true;
			}
			// TODO 判断是否可以消行
			// TODO 如果可以消行 消行操作 // 并计算分数
			int exp = this.plusExp();
			if (exp > 0) {
				this.plusPoint(exp);
			}

			// TODO 算分操作

			// TODO 是否可以升级
			// TODO 升级
			// 刷新一个新的方块

			// 刷新新的方块
			this.dto.getGameAct().init(this.dto.getNext());
			// 随机生成一个方块
			this.dto.setNext(random.nextInt(MAX_TYPE));
			// 检查游戏是否失败
			if (isLose()) {
				//结束游戏
				this.dto.setStart(false);
			}
			return true;
		}
	}

	/**
	 * 检查游戏是否失败
	 * 
	 * @return
	 */
	private boolean isLose() {
		// 获得现在的活动方块
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		// 获得现在的游戏地图
		boolean[][] map = this.dto.getGameMap();
		for (int i = 0; i < actPoints.length; i++) {
			if (map[actPoints[i].x][actPoints[i].y]) {
				return true;
			}
		}
		return false;
	}

	// 升级操作
	private void plusPoint(int exp) {
		int level = this.dto.getNowLevel();
		int rmline = this.dto.getNowRemoveLine();
		int point = this.dto.getNowPoint();
		if (rmline != 0) {
			
			if (rmline % LEVEL_UP == 0) {
				level++;
			}
		}

		this.dto.setNowLevel(level);
		this.dto.setNowRemoveLine(rmline + exp);

		this.dto.setNowPoint(point + PLUS_POINT[exp - 1]);
	}

	// 返回加分
	private int plusExp() {
		// TODO Auto-generated method stub
		// 获得游戏地图
		boolean[][] map = this.dto.getGameMap();
		int exp = 0;
		// 扫描 查看是否有可以的

		for (int y = 0; y < 18; y++) {
			//
			if (this.isCanRemoveLine(y, map)) {
				this.removeLine(y, map);
				exp++;
			}

		}
		return exp;
	}

	// 消行处理
	private void removeLine(int rowNumber, boolean[][] map) {
		for (int x = 0; x < 10; x++) {
			for (int y = rowNumber; y > 0; y--) {
				map[x][y] = map[x][y - 1];
			}
			map[x][0] = false;
		}
	}

	// 判断某一行是否可消除
	private boolean isCanRemoveLine(int y, boolean[][] map) {
		for (int x = 0; x < 10; x++) {
			if (!map[x][y]) {
				// 如果有一个为假 直接跳到下一行
				return false;
			}

		}
		return true;
	}

	// 左边
	@Override
	public boolean keyLeft() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
			return true;
		}
	}

	// 右边
	@Override
	public boolean keyRight() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
			return true;
		}
	}

	// TODO -----------------------------------------------------------------

	public void testLevelUp() {

	}

	@Override
	public boolean keyFunUp() {
		// 作弊
		this.dto.setCheat(true);
		this.plusPoint(4);
		// // TODO Auto-generated method stub
		// int level = this.dto.getNowLevel();
		// int point = this.dto.getNowPoint();
		// int rmline = this.dto.getNowRemoveLine();
		// point += 10;
		// rmline += 1;
		// if (rmline % 20 == 0) {
		// level += 1;
		// }
		// this.dto.setNowPoint(point);
		// this.dto.setNowLevel(level);
		// this.dto.setNowRemoveLine(rmline);
		return true;
	}

	@Override
	public boolean keyFunDown() {
		if(this.dto.isPause()){
			return true;
		}
		while (!this.keyDown());
		// 瞬间下落
		return true;
	}

	@Override
	public boolean keyFunLeft() {
		this.dto.changeShowShadow();
		// 阴影开关
		return true;
	}

	@Override
	public boolean keyFunRight() {
		// 暂停
		if(this.dto.isStart()){
			this.dto.changePause();
		}
		
		return true;
	}

	@Override
	public void startGame() {
		// 随机生成下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
		// 随机生成现在方块
		dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		// 把游戏状态设为开始
		this.dto.setStart(true);
		//dto初始化
		this.dto.dtoInit();
	}

	@Override
	public void mainAction() {
		this.keyDown();
	}

}
