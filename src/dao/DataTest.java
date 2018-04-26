package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Player;

public  class DataTest implements Data{

	@Override
	public List<Player> loadData() {
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("zxn",6000));
		players.add(new Player("czl",3000));
		players.add(new Player("hehe",2500));
		players.add(new Player("Ð¡ºì",300));
		players.add(new Player("haha",600));
		return players;
	}



	@Override
	public void saveData(Player players) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
