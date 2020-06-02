package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pais;
import model.Usuario;

public class UsuarioDAO 
{
	public Usuario readLogin()
	{
		Usuario usuario = new Usuario();
		String sqlRead = "SELECT nome, login, senha FROM paises.usuario";
		
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlRead);)
		{
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				usuario.setNome(resultSet.getString("nome"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
			}
		}
		catch(SQLException error)
		{
			System.out.println("Erro como o mysql: "+error);
		}
		catch(ClassNotFoundException error)
		{
			System.out.println("Erro ao obter a conexao: "+error);
		}
		return usuario;
	}
	
	public int create(Usuario usuario)
	{
		String sqlInsert = "INSERT INTO paises.usuario_cripto(nome, login, senha_cripto) VALUES(?, ?, ?)";
		try(Connection connection = ConnectionFactory.obtemConexao(); 
			PreparedStatement statement = connection.prepareStatement(sqlInsert);) 
		{
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getLogin());
			statement.setBytes(3, usuario.getSenhaCripto());
			statement.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement statement2 = connection.prepareStatement(sqlQuery);
				ResultSet resultSet = statement2.executeQuery();) 
			{
				if(resultSet.next())
				{
					usuario.setId(resultSet.getInt(1));
				}
			} 
			catch (SQLException error) 
			{
				System.out.println("Erro com codigo MySQL - CREATE - Retorno do banco de dados"+error);
			}
		} 
		catch(ClassNotFoundException error)
		{
			System.out.println("Erro ao conectar ao banco - CREATE - Inserir dados"+error);
		}
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - CREATE - Inserir dados "+error);
		} 
		return usuario.getId();
	}
	
	public Usuario readLoginCripto()
	{
		Usuario usuario = new Usuario();
		String sqlRead = "SELECT nome, login, senha_cripto FROM paises.usuario_cripto";
		
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlRead);)
		{
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				usuario.setNome(resultSet.getString("nome"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenhaCripto(resultSet.getBytes("senha_cripto"));
			}
		}
		catch(SQLException error)
		{
			System.out.println("Erro como o mysql: "+error);
		}
		catch(ClassNotFoundException error)
		{
			System.out.println("Erro ao obter a conexao: "+error);
		}
		return usuario;
	}
}
