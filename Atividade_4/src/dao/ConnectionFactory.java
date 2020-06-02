package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory 
{
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/paises?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "ana98";
	
	public static Connection obtemConexao() throws ClassNotFoundException, SQLException
	{
		try 
		{
			Class.forName(DRIVER);
		}
		catch(ClassNotFoundException error)
		{
			System.out.println("Erro ao pegar o DRIVER");
		}
		
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
