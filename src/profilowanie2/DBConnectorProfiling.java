package profilowanie2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.javafx.collections.SetListenerHelper;

public class DBConnectorProfiling {
	
	private static DBConnectorProfiling instance;
	
	private Connection c;
	private Statement statement;
	
	private DBConnectorProfiling(){
		
	}
	
	public boolean connect(String driver, String url){
		
		if(statement != null){
			try {
				statement.close();
				c.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(url);
			statement = c.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c != null ? true : false;
	}
	
	public boolean close(){
		
		if(statement != null){
			try {
				statement.close();
				c.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		c = null;
		statement = null;
		
		return true;
		
	}
	
	public int excuteUpdate(String update){
		
		if(c == null) return -1;
		
		try {
			return statement.executeUpdate(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return -1;
		}
		
	}
	
	public ResultSet excuteQuery(String query){
		
		if(c == null) return null;
		
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	public static DBConnectorProfiling getInstance(){
		if(instance == null) instance = new DBConnectorProfiling();
		return instance;
	}
	
}
