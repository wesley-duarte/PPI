package aula01_PPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pais 
{
	private int ID;
	private String Nome;
	private long Populacao;
	private double Area;
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/paises?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "ana98";
	
	public static Connection connectionDataBase() throws ClassNotFoundException, SQLException
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
	
	public Pais()
	{
		
	}
	
	public Pais(int iD, String Nome, long Populacao, double Area) 
	{
		super(); 
		ID = iD; 
		this.Nome = Nome;
		this.Populacao = Populacao;
		this.Area = Area;
	}

	//Inicio Getters e Setters
	public int getID() 
	{
		return ID;
	}

	public void setID(int ID)
	{
		this.ID = ID;
	}

	public String getNome() 
	{
		return Nome;
	}

	public void setNome(String Nome) 
	{
		this.Nome = Nome;
	}

	public long getPopulacao()
	{
		return Populacao;
	}

	public void setPopulacao(long populacao) 
	{
		this.Populacao = populacao;
	}

	public double getArea() 
	{
		return Area;
	}

	public void setArea(double area) 
	{
		this.Area = area;
	}
	//Fim Getters e Setters
	
	@Override
	public String toString() {
		return "[ID=" + ID + ", Nome=" + Nome + ", Populacao=" + Populacao + ", Area=" + Area + "]";
	}
	
	//----- C R E A T E -----
	public void create()
	{
		//Inserir dados
		String sqlInsert = "INSERT INTO pais(Nome, Populacao, Area) VALUES(?, ?, ?)";
		try(Connection connection = connectionDataBase(); 
			PreparedStatement statement = connection.prepareStatement(sqlInsert);) 
		{
			statement.setString(1, getNome());
			statement.setLong(2, getPopulacao());
			statement.setDouble(3, getArea());
			statement.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement statement2 = connection.prepareStatement(sqlQuery);
				ResultSet resultSet = statement2.executeQuery();) 
			{
				if(resultSet.next())
				{
					setID(resultSet.getInt(1));
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
			System.out.println("Erro com codigo MySQL - CREATE - Inserir dados"+error);
		} 
	}
	
	//----- R E A D -----
	public String read()
	{
		String sqlRead = "SELECT Nome, Populacao, Area FROM Pais WHERE Pais.ID = ?";
		Pais pais = new Pais();
		
		try(Connection connection = connectionDataBase();
			PreparedStatement statement = connection.prepareStatement(sqlRead);)
		{
			statement.setInt(1, getID());
			try(ResultSet resultSet = statement.executeQuery();)
			{
				if(resultSet.next())
				{
					pais.setNome(resultSet.getString("Nome"));
					pais.setPopulacao(resultSet.getLong("Populacao"));
					pais.setArea(resultSet.getDouble("Area"));
				}
			}
			catch (SQLException error) 
			{
				System.out.println("Erro com codigo MySQL - READ - ResultSet"+error);
			} 
			
		} 
		catch (ClassNotFoundException error)
		{
			System.out.println("Erro ao conectar ao banco - READ"+error);
		}
		catch (SQLException error)
		{
			System.out.println("Erro com codigo MySQL - READ"+error);
		}
		return pais.toString();
	}
	
	//----- U P D A T E -----
	public void update()
	{
		String sqlUpdate = "UPDATE Pais SET Nome = ?, Populacao = ?, Area = ? WHERE ID = ?";
		try(Connection connection = connectionDataBase();
			PreparedStatement statement = connection.prepareStatement(sqlUpdate);) 
		{
			statement.setString(1, getNome());
			statement.setLong(2, getPopulacao());
			statement.setDouble(3, getArea());
			statement.setInt(4, getID());
			statement.execute();
		} 
		catch (ClassNotFoundException error) 
		{
			System.out.println("Erro ao conectar ao banco - UPDATE"+error);
		}
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - UPDATE"+error);
		} 
	}
	
	//----- D E L E T E -----
	public void delete()
	{
		String sqlDelete = "DELETE FROM Pais WHERE ID = ?";
		try(Connection connection = connectionDataBase();
			PreparedStatement statement = connection.prepareStatement(sqlDelete);)
		{
			statement.setInt(1, getID());
		} 
		catch (ClassNotFoundException error) 
		{
			System.out.println("Erro ao conectar ao banco - DELETE"+error);
		}
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - DELETE"+error);
		} 
	}
	
	//retorna pais com maior populacao
	public String maiorPopulacao()
	{
		Pais pais = new Pais();
		String sqlMaxPopulacao = "SELECT Nome, Populacao FROM Pais WHERE Populacao = (SELECT MAX(Populacao) FROM Pais)";
		try(Connection connection = connectionDataBase();
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
		try(Connection connection = connectionDataBase();
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
		try(Connection connection = connectionDataBase();
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
