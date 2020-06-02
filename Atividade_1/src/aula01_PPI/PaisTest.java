package aula01_PPI;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PaisTest 
{
	Pais pais, paisCopia;
	static int ID = 0;
	
	@Before
	public void setUp()
	{
		System.out.println("_______________________________________________________________________________________");
		System.out.println("Set Up");
		Pais pais = new Pais(ID, "Mexico", 12367532, 1958201);
		System.out.println("Pais: "+pais.toString());
		Pais paisCopia = new Pais(ID, "Mexico", 12367532, 1958201);
		System.out.println("Copia de Pais: "+paisCopia.toString());
		System.out.println("_______________________________________________________________________________________");
	}
	
	@Test
	public void testeCreate()
	{
		System.out.println("Teste create()");
		pais.create();
		ID = pais.getID();
		System.out.println("ID: "+ID);
		paisCopia.setID(ID);
		assertEquals("Testa o metodo create", pais, paisCopia);
	}
	
	@Test
	public void testeRead()
	{
		System.out.println("Teste read()");
		Pais paisBanco = new Pais(1, "Brasil", 210000000, 8515767.04);
		System.out.println("paisBanco: "+paisBanco);
		Pais novo = new Pais(1, null, 0, 0);
		System.out.println("novo: "+novo);
		novo.read();
		assertEquals("Testa o metodo read", novo, paisBanco);
	}
	
	@Test
	public void testeUpdate()
	{
		System.out.println("Teste update()");
		pais.setNome("Holanda");
		pais.setPopulacao(17100475);
		pais.setArea(41528);
		paisCopia.setNome("Holanda");
		paisCopia.setPopulacao(17100475);
		paisCopia.setArea(41528);
		pais.update();
		assertEquals("Testa o metodo update", pais, paisCopia);
	}
	
	@Test
	public void delete()
	{
		System.out.println("Teste delete()");
		paisCopia.setID(-1);
		paisCopia.setNome(null);
		paisCopia.setPopulacao(0);
		paisCopia.setArea(0);
		pais.delete();
		assertEquals("Testa o metodo delete", pais, paisCopia);
	}
		
}
