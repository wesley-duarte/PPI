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
}
