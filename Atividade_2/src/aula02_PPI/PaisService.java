package aula02_PPI;

import java.util.ArrayList;

public class PaisService 
{
	PaisDAO dao = new PaisDAO(); 
	PaisMetodos paisMetodos = new PaisMetodos();
	
	//----- C R E A T E -----
	public int create(Pais pais)
	{
		return dao.create(pais);
	}
	
	//----- R E A D -----
	public Pais read(int id)
	{
		return dao.read(id);
	}
	
	//----- U P D A T E -----
	public void update(Pais pais)
	{
		dao.update(pais);
	}
	
	//----- D E L E T E -----
	public void delete(int id)
	{
		dao.delete(id);
	}
	
	// Pais com maior populacao
	public String maiorPopulacao()
	{
		return paisMetodos.maiorPopulacao();
	}
	
	// Pais com menor area
	public String menorArea()
	{
		return paisMetodos.menorArea();
	}
	
	// Vetor de 3 paises
	public ArrayList<Pais> vetorTresPaises()
	{
		return paisMetodos.vetorTresPaises();
	}
}
