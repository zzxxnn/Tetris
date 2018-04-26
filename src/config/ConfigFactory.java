package config;


public class ConfigFactory {
	private static GameConfig GAME_CONFIG = null;
	static {

		try {
			GAME_CONFIG = new GameConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static GameConfig getGAME_CONFIG() {
		return GAME_CONFIG;
	}

	public static GameConfig getGmaeConfig() {

		return GAME_CONFIG;
	}
}
