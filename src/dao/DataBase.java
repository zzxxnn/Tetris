package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DBCPUtil;
import dto.Player;

public class DataBase extends DBCPUtil implements Data {
	
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());

//	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//
//	private static String DB_URI = "jdbc:sqlserver://127.0.0.1:1433;database=game_test";
//
//	private static String DB_USER = "gm";
//
//	private static String DB_PWD = "gm123";
//	
//	private static String LOAD_SQL = "select top 5 user_name,point  from user_point where type_id = 1 order by point desc";
//	
//	private static String SAVE_SQL = "insert into user_point(user_name,point,type_id) values (,,);";
//	
//	static{
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public List<Player> loadData() {
//		Connection com = null;
//		PreparedStatement stmtPreparedStatement = null;
//		ResultSet ra = null;
//		List<Player> players = new ArrayList<Player>();
//		try {
//			com = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
//			stmtPreparedStatement = com.prepareStatement(LOAD_SQL);
//			ra = stmtPreparedStatement.executeQuery();
//			while (ra.next()) {
//				players.add(new Player(ra.getString(1),ra.getInt(2)));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(com != null)com.close();
//				if(stmtPreparedStatement != null)stmtPreparedStatement.close();
//				if(ra != null)ra.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return players;
//	}
	@Override
	public List<Player> loadData() {
		try {
			return qr.query("select * from player", new BeanListHandler<Player>(Player.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

//	public void saveData(Player players) {
//		Connection com = null;
//		PreparedStatement stmtPreparedStatement = null;
//		try {
//			com = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
//			stmtPreparedStatement = com.prepareStatement(SAVE_SQL);
//			stmtPreparedStatement.setObject(1, players.getName());
//			stmtPreparedStatement.setObject(2, players.getPoint());
//			stmtPreparedStatement.setObject(3, 1);
//			stmtPreparedStatement.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(com != null)com.close();
//				if(stmtPreparedStatement != null)stmtPreparedStatement.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	@Override
	public void saveData(Player players) {
		try {
			qr.update("insert into player(name,point) values(?,?)", players.getName(),players.getPoint());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
