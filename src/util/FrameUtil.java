package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {
	public static void setFrameCenter(JFrame jf) {
		Toolkit asd = Toolkit.getDefaultToolkit();
		Dimension screen = asd.getScreenSize();
		int w = screen.width - jf.getWidth() >> 1;
		int y = (screen.height - jf.getHeight() >> 1) - 32;
		jf.setLocation(w, y);
	}
}
