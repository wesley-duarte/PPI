package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

@WebServlet("/PaisController.do")
public class PaisController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		doPost(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		String nome = request.getParameter("nome");
		long populacao = Long.parseLong(request.getParameter("populacao"));
		double area = Double.parseDouble(request.getParameter("area"));
		String acao = request.getParameter("acao");
		
		Pais pais = new Pais();
		pais.setNome(nome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
			
		PaisService paisService = new PaisService();
		RequestDispatcher dispatcher = null;
		
		switch(acao)
		{
			case "Incluir":
				paisService.create(pais);
				pais = paisService.read(pais.getID());
				request.setAttribute("pais", pais);
				dispatcher = request.getRequestDispatcher("Pais.jsp");
				break;
				
			case "Listar":
				ArrayList<Pais> paises = paisService.listarTodos();
				request.setAttribute("paises", paises);
				dispatcher = request.getRequestDispatcher("ListaDePaises.jsp");
		}
		
		try 
		{
			dispatcher.forward(request, response);
		}
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		}
			
				/*PrintWriter writer = response.getWriter();
				writer.println("<html><head><title> Cadastro de Pais </title></head><body><h3>Pais Cadastrado</h3>");
				writer.println("ID: "+pais.getID()+"<br>");
				writer.println("Nome: "+pais.getNome()+"<br>");
				writer.println("População: "+pais.getPopulacao()+"<br>");
				writer.println("Area: "+pais.getArea()+"<br>");
				writer.println("</body></html>");*/
	}
}


