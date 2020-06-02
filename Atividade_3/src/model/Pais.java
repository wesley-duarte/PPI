package model;

import java.io.Serializable;

public class Pais implements Serializable 
{
	private static final long serialVersionUID = 1L;
	int ID;
	String nome;
	long Populacao;
	double Area;
	
	public Pais()
	{
		
	}
	
	public Pais(int ID, String Nome, long Populacao, double Area)
	{
		this.ID = ID;
		this.nome = Nome;
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
		return nome;
	}

	public void setNome(String Nome) 
	{
		this.nome = Nome;
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
		return "[ID=" + ID + ", Nome=" + nome + ", Populacao=" + Populacao + ", Area=" + Area + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Area);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ID;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (int) (Populacao ^ (Populacao >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (Double.doubleToLongBits(Area) != Double.doubleToLongBits(other.Area))
			return false;
		if (ID != other.ID)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Populacao != other.Populacao)
			return false;
		return true;
	}
}
