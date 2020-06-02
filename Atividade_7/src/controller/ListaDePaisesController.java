package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.PaisService;
import model.Pais;

@WebServlet("/ListaDePaises.do")
public class ListaDePaisesController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String chave = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		
		PaisService paisService = new PaisService();
		ArrayList<Pais> lista = null;
		HttpSession session = request.getSession();
		
		String nome = request.getParameter("nome");
		String populacao = request.getParameter("populacao");
		String area = request.getParameter("area");
		
		if(acao.equals("Buscar"))
		{
			if(chave != null && chave.length()>0)
			{
				try 
				{
					lista = paisService.listaDePaises(chave);
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				lista = paisService.listaDePaises();
			}
			session.setAttribute("lista", lista);
		}
		else if(acao.equals("Reiniciar"))
		{
			session.setAttribute("lista", null);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListaDePaises.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
