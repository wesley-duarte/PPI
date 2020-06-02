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

import model.Pais;
import service.PaisService;

@WebServlet("/PaisController.do")
public class PaisController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8");
		String pAcao = request.getParameter("acao");
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		int id = -1;
		long populacao = -1;
		double area = -1;
		try {
			id = Integer.parseInt(pId);
			
		} catch (NumberFormatException e) {

		}
		try {
			populacao = Long.parseLong(pPopulacao);
			area = Double.parseDouble(pArea);
			
		} catch (NumberFormatException e) {

		}

		Pais pais = new Pais();
		pais.setID(id);
		pais.setNome(pNome);
		pais.setPopulacao(populacao);
		pais.setArea(area);

		// instanciar o service
		PaisService cs = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		if (pAcao.equals("Criar")) {
			cs.create(pais);
			ArrayList<Pais> lista = new ArrayList<>();
			
			lista.add(pais);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListaDePaises.jsp");
		} else if (pAcao.equals("Excluir")) {
			cs.delete(pais.getID());
			ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
			lista.remove(busca(pais, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("index.jsp");
		} else if (pAcao.equals("Alterar")) {
			cs.update(pais);
			ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
			int pos = busca(pais, lista);
			lista.remove(pos);
			lista.add(pos, pais);
			session.setAttribute("lista", lista);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
		} else if (pAcao.equals("Visualizar")) {
			pais = cs.read(pais.getID());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
		} else if (pAcao.equals("Editar")) {
			pais = cs.read(pais.getID());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("AlterarPais.jsp");
		}
		view.forward(request, response);
	}
	
	public int busca(Pais pais, ArrayList<Pais> lista)
	{
		Pais to;
		for(int i=0; i < lista.size(); i=i+1)
		{
			to = lista.get(i);
			if(to.getID() == pais.getID());
			return i;
		}
		return -1;
	}
}
