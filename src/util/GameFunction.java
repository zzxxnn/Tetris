package util;

public class GameFunction {
	/**
	 * �����߳�˯��ʱ��
	 * @param level
	 * @return
	 */
	public static long getSleepTimeLevel(int level){
		long sleep = (-40*level+740); 
		sleep = sleep<100?100:sleep;
		return sleep;
	}
}
