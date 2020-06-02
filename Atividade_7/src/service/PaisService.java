package service;

import java.util.ArrayList;

import dao.PaisDAO;
import dao.PaisMetodos;
import model.Pais;

public class PaisService 
{
	PaisDAO dao;
	PaisMetodos paisMetodos;
	
	public PaisService()
	{
		dao = new PaisDAO();
		paisMetodos = new PaisMetodos();
	}
	
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
	
	//Lista de paises
	public ArrayList<Pais> listaDePaises()
	{
		return dao.listarDePaises();
	}
	
	//Lista de paises com chave
	public ArrayList<Pais> listaDePaises(String chave) throws ClassNotFoundException
	{
		return dao.listaDePaises(chave);
	}
}
