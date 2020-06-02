package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;

public class PaisMetodos 
{
	//retorna pais com maior populacao
	public String maiorPopulacao()
	{
		Pais pais = new Pais();
		String sqlMaxPopulacao = "SELECT Nome, Populacao FROM Pais WHERE Populacao = (SELECT MAX(Populacao) FROM Pais)";
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlMaxPopulacao);)
		{
			statement.execute();
			try(ResultSet resultSet = statement.executeQuery();)
			{
				if(resultSet.next())
				{
					pais.setNome(resultSet.getString("Nome"));
					pais.setPopulacao(resultSet.getLong("Populacao"));
				}
			}
			catch (SQLException error) 
			{
				System.out.println("Erro com ResultSet - maiorPopulacao()"+error);
			} 
		} 
		catch (ClassNotFoundException error) 
		{
			System.out.println("Erro ao conectar ao banco - maiorPopulacao()"+error);
		} 
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - maiorPopulacao()"+error);
		}
		
		return "Pais com maior populacao: "+pais.getNome();
	}
	
	//retorna pais com menor area
	public String menorArea()
	{
		Pais pais = new Pais();
		String sqlMinArea = "SELECT Nome, Area, MIN(Area) FROM Pais GROUP BY Nome ORDER BY Populacao";
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlMinArea);)
		{
			try(ResultSet resultSet = statement.executeQuery();)
			{
				if(resultSet.next())
				{
					pais.setNome(resultSet.getString("Nome"));
					pais.setArea(resultSet.getDouble("Area"));
				}
	
			}
			catch (SQLException error) 
			{
				System.out.println("Erro com ResultSet - menorArea()"+error);
			} 
		} 
		catch (ClassNotFoundException error) 
		{
			System.out.println("Erro ao conectar ao banco - menorArea()"+error);
		}
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - menorArea()"+error);
		} 
		
		return "Pais com menor area: "+pais.getNome();
	}
	
	//retorna um vetor de 3 paises
	public ArrayList<Pais> vetorTresPaises()
	{
		ArrayList<Pais> vetor = new ArrayList<>();
		String sqlVetor = "SELECT ID, Nome, Populacao, Area FROM Pais";
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlVetor);)
			{
				try(ResultSet resultSet = statement.executeQuery();)
				{
					for(int i=0; i<3; i=i+1)
					{
						if(resultSet.next())
						{
							Pais pais = new Pais();
							pais.setID(resultSet.getInt("ID"));
							pais.setNome(resultSet.getString("Nome"));
							pais.setPopulacao(resultSet.getLong("Populacao"));
							pais.setArea(resultSet.getDouble("Area"));
							vetor.add(pais);
						}
					}
				}
				catch (SQLException error) 
				{
					System.out.println("Erro com ResultSet - vetorTresPiases()"+error);
				} 
			} 
			catch (ClassNotFoundException error)
			{
				System.out.println("Erro ao conectar ao banco - vetorTresPiases()"+error);
			}
			catch (SQLException error) 
			{
				System.out.println("Erro com codigo MySQL - vetorTresPiases()"+error);
			} 
			
		return vetor;
	}
}
