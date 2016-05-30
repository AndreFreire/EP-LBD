package DAO;
import java.sql.*;

public class ConnectionFactory {
	
	public Connection getConnection(){
		try{
			return DriverManager.getConnection("jdbc:mysql://localhost/gym", "root", "caio10");
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
