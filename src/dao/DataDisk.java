package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import dao.Data;
import dto.Player;

public class DataDisk implements Data {

	private static final String haha = "data/save.dat";

	@Override
	public List<Player> loadData() {
		ObjectInputStream ois = null;
		List<Player> play = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(haha));
			play = (List<Player>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return play;
	}

	@Override
	public void saveData(Player players) {
		List<Player> players2 = this.loadData();
//		System.out.println(players2.size());
//		for (int i = 0; i < players2.size(); i++) {
//			players2.remove(i);
//			i--;
//		}
//		System.out.println(players2.size());
		players2.add(players);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(haha));
			oos.writeObject(players2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
//	public static void main(String[] args) {
//		List<Player> players = new DataTest().loadData();
//		
//		DataDisk aDataDisk = new DataDisk();
//		aDataDisk.saveData(players.get(0));
//		
////		List<Player> p = aDataDisk.loadData();
////		for(Player pp:p){
////			System.out.println(pp.getName());
////		}
//	}




}
