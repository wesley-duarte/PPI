package controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cryptography.AlgoritmoAES;
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
		
		Usuario usuario = new Usuario();
		AlgoritmoAES algoritmoAES = new AlgoritmoAES();
		byte[] senhaByte = senha.getBytes();
		
		System.out.println("_____________________________________________");
		System.out.println("Bytes da senha: "+senhaByte);
		System.out.println("_____________________________________________");
		
		try 
		{
			algoritmoAES.geradorDeCifra(senhaByte);
		} 
		catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | IOException e)
		{
			e.printStackTrace();
		}
		
		byte[] senhaCifrada = algoritmoAES.getTextoCifrado();
		
		System.out.println("Login: "+login);
		System.out.println("Senha: "+senha);
		System.out.println("Senha criptografada: "+senhaCifrada);
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		UsuarioService usuarioService = new UsuarioService();
		usuario = usuarioService.readLoginCripto();
		System.out.println("Senha do banco: "+usuario.getSenhaCripto());
		
		Log log = new Log();
		TentativasLog tentativasLog = new TentativasLog();
		
		if(acao.equals("Login"))
		{
			if(login.equals(usuario.getLogin()) && senhaCifrada == usuario.getSenhaCripto())
			{
				session.setAttribute("usuarioLogado", usuario);
				view = request.getRequestDispatcher("index.jsp");
				log.registrarLog(usuario);
			}
			else if(login.equals(usuario.getLogin()) && senhaCifrada != usuario.getSenhaCripto())
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
