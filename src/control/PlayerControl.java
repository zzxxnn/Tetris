package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControl extends KeyAdapter {
	private GameControl gameControl;

	public PlayerControl(GameControl ctrl) {
		this.gameControl = ctrl;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKeyCode(e.getKeyCode());
//		System.out.println(e.getKeyCode());
	}

}
