package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import logs.Log;
import logs.TentativasLog;
import model.Usuario;
import service.UsuarioService;

@WebServlet("/UsuarioController.do")
public class UsuarioController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		System.out.println("Login: "+login);
		System.out.println("Senha: "+senha);
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		Usuario usuario = new Usuario();
		UsuarioService usuarioService = new UsuarioService();
		usuario = usuarioService.readLogin();
		
		Log log = new Log();
		TentativasLog tentativasLog = new TentativasLog();
		
		if(acao.equals("Login"))
		{
			if(login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha()))
			{
				session.setAttribute("usuarioLogado", usuario);
				view = request.getRequestDispatcher("index.jsp");
				log.registrarLog(usuario);
			}
			else if(login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha()) != true)
			{
				tentativasLog.registrarTentadivasLogSenhaErrada(login, senha);
				view = request.getRequestDispatcher("login.jsp");
			}
			else if(login.equals(usuario.getLogin()) != true)
			{
				tentativasLog.registrarTentadivasLogUsuarioInvalido(login, senha);
				view = request.getRequestDispatcher("login.jsp");
			}
		}
		view.forward(request, response);
	}
}
