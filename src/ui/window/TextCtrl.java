package ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextCtrl extends JTextField {

	private int keyCode;

	private String methodName;

	public TextCtrl(int x, int y, int w, int h, String methodName) {
		// 设置文本框位置
		this.setBounds(x, y, w, h);

		// 初始化方法名
		this.methodName = methodName;

		// 增加事件监听

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				setText(null);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setText(KeyEvent.getKeyText((e.getKeyCode())));
				setKeyCode(e.getKeyCode());
			}

			@Override
			public void keyPressed(KeyEvent e) {
				setText(null);
			}
		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		// 初始化keycode
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(keyCode));
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
