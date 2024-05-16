package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {
	
	private Connection c;

	public GenericDao() {
		super();
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "teste";
		String user = "root";
		String senha = "";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		c = DriverManager.getConnection(
				String.format("jdbc:mysql://%s:3306/%s", hostName, dbName),
				user, senha);	
		return c;
	}		
}
