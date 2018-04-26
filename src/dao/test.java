package dao;

import org.junit.Before;
import org.junit.Test;

import dto.Player;

public class test {
	DataBase base;
	@Before
	public void hehe() {
		base = new DataBase();
	}

	@Test
	public void testLoadData() {
		
		System.out.println(base.loadData());
	}

	@Test
	public void testSaveData() {
		Player player = new Player("dgsdfdf", 12312);
		base.saveData(player);

	}

}
