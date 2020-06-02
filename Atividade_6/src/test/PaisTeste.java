package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Pais;
import service.PaisService;

public class PaisTeste 
{
	Pais pais, paisCopia;
	PaisService paisService;
	static int ID = 0;
	
	@Before
	public void test() 
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.println("S E T U P");
		
		pais = new Pais();
		pais.setID(ID);
		pais.setNome("Mexico");
		pais.setPopulacao(12367532);
		pais.setArea(1958201);
		
		paisCopia = new Pais();
		paisCopia.setID(ID);
		paisCopia.setNome("Mexico");
		paisCopia.setPopulacao(12367532);
		paisCopia.setArea(1958201);
		
		//paisService = new PaisService();
		
		System.out.println("Pais: "+pais);
		System.out.println("Copia de Pais: "+paisCopia);
		System.out.println("ID: "+ID);
		System.out.println("----------------------------------------------------------------------");
	}
	
	@Test
	public void testRead()
	{			
		System.out.println("R E A D   T E S T");
		
		Pais paisBanco = new Pais();
		paisBanco.setID(1);
		paisBanco.setNome("Brasil");
		paisBanco.setPopulacao(210000000);
		paisBanco.setArea(8515767.04);
		
		PaisService novoPaisService = new PaisService();
		Pais novo = novoPaisService.read(1);
		assertEquals("Read test", novo, paisBanco);
	}
	
	@Test
	public void testCreate()
	{
		PaisService paisService = new PaisService();
		
		System.out.println("C R E A T E   T E S T");
		
		ID = paisService.create(pais);
		System.out.println("ID: "+ID);
		paisCopia.setID(ID);
		assertEquals("Create test", pais, paisCopia);
	}
	
	@Test
	public void testUpdate()
	{
		PaisService paisService = new PaisService();
		
		System.out.println("U P D A T E   T E S T");
		
		pais.setNome("Mexico2");
		pais.setPopulacao(99999999);
		pais.setArea(99999999);
		
		paisCopia.setNome("Mexico2");
		paisCopia.setPopulacao(99999999);
		paisCopia.setArea(99999999);
		
		paisService.update(pais);
		pais = paisService.read(pais.getID());
		assertEquals("Update test", pais, paisCopia);
	}
	
	@Test
	public void testDelete()
	{
		PaisService paisService = new PaisService();
		
		System.out.println("D E L E T E   T E S T");
		
		paisCopia.setID(-1);
		paisCopia.setNome(null);
		paisCopia.setPopulacao(0);
		paisCopia.setArea(0);
		
		paisService.delete(ID);
		pais = paisService.read(ID);
		assertEquals("Delete test", pais, paisCopia);
	}
}