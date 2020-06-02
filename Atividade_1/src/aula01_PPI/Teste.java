package aula01_PPI;

public class Teste 
{
	
	public static void main(String[] args)
	{
		Pais teste = new Pais();
		Pais mexico = new Pais(6, "Mexico", 12367532, 1958201);
		Pais teste2 = new Pais(11, null, 0, 0);
		Pais mexico2 = new Pais(6, "Mexico", 20000000, 2021958201);
		
		System.out.println(teste.maiorPopulacao());
		System.out.println(teste.menorArea());
		System.out.println(teste.vetorTresPaises());
		//mexico.create();
		//System.out.println(teste2.read());
		//mexico2.update();
		//teste2.delete();
	}
}
