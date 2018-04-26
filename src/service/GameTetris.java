package service;

import java.awt.Point;
import java.util.Random;

import dto.GameDto;
import entity.GameAct;

public class GameTetris implements GameService {

	private GameDto dto;
	// �����������
	private Random random = new Random();

	private static final int MAX_TYPE = 6;

	private static final int LEVEL_UP = 20;
	// ��������
	private static final int PLUS_POINT[] = { 10, 30, 60, 100 };

	public GameTetris(GameDto dto) {
		this.dto = dto;

	}

	// ��ת
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

	// ����
	@Override
	public boolean keyDown() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized (this.dto) {
			// ���������ƶ����ж��Ƿ�ɹ�
			if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
				return false;
			}
			// �����Ϸ��ͼ����
			boolean[][] map = this.dto.getGameMap();
			// ��÷������
			Point[] act = this.dto.getGameAct().getActPoints();
			// ������ѻ�����ͼ����
			for (int i = 0; i < act.length; i++) {
				map[act[i].x][act[i].y] = true;
			}
			// TODO �ж��Ƿ��������
			// TODO ����������� ���в��� // ���������
			int exp = this.plusExp();
			if (exp > 0) {
				this.plusPoint(exp);
			}

			// TODO ��ֲ���

			// TODO �Ƿ��������
			// TODO ����
			// ˢ��һ���µķ���

			// ˢ���µķ���
			this.dto.getGameAct().init(this.dto.getNext());
			// �������һ������
			this.dto.setNext(random.nextInt(MAX_TYPE));
			// �����Ϸ�Ƿ�ʧ��
			if (isLose()) {
				//������Ϸ
				this.dto.setStart(false);
			}
			return true;
		}
	}

	/**
	 * �����Ϸ�Ƿ�ʧ��
	 * 
	 * @return
	 */
	private boolean isLose() {
		// ������ڵĻ����
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		// ������ڵ���Ϸ��ͼ
		boolean[][] map = this.dto.getGameMap();
		for (int i = 0; i < actPoints.length; i++) {
			if (map[actPoints[i].x][actPoints[i].y]) {
				return true;
			}
		}
		return false;
	}

	// ��������
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

	// ���ؼӷ�
	private int plusExp() {
		// TODO Auto-generated method stub
		// �����Ϸ��ͼ
		boolean[][] map = this.dto.getGameMap();
		int exp = 0;
		// ɨ�� �鿴�Ƿ��п��Ե�

		for (int y = 0; y < 18; y++) {
			//
			if (this.isCanRemoveLine(y, map)) {
				this.removeLine(y, map);
				exp++;
			}

		}
		return exp;
	}

	// ���д���
	private void removeLine(int rowNumber, boolean[][] map) {
		for (int x = 0; x < 10; x++) {
			for (int y = rowNumber; y > 0; y--) {
				map[x][y] = map[x][y - 1];
			}
			map[x][0] = false;
		}
	}

	// �ж�ĳһ���Ƿ������
	private boolean isCanRemoveLine(int y, boolean[][] map) {
		for (int x = 0; x < 10; x++) {
			if (!map[x][y]) {
				// �����һ��Ϊ�� ֱ��������һ��
				return false;
			}

		}
		return true;
	}

	// ���
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

	// �ұ�
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
		// ����
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
		// ˲������
		return true;
	}

	@Override
	public boolean keyFunLeft() {
		this.dto.changeShowShadow();
		// ��Ӱ����
		return true;
	}

	@Override
	public boolean keyFunRight() {
		// ��ͣ
		if(this.dto.isStart()){
			this.dto.changePause();
		}
		
		return true;
	}

	@Override
	public void startGame() {
		// ���������һ������
		this.dto.setNext(random.nextInt(MAX_TYPE));
		// ����������ڷ���
		dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		// ����Ϸ״̬��Ϊ��ʼ
		this.dto.setStart(true);
		//dto��ʼ��
		this.dto.dtoInit();
	}

	@Override
	public void mainAction() {
		this.keyDown();
	}

}
