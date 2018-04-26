package main;

import javax.swing.JOptionPane;

import control.GameControl;

public class Main {
	public static void main(String[] args) {
		
		try {
			new GameControl();
		} catch (Exception e) {
			/**
			 * 
			 */
			// TODO Auto-generated catch bloc
			JOptionPane.showMessageDialog(null, "ÔËÐÐ³ö´í");
			e.printStackTrace();
		}

	}
}
 