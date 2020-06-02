package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService 
{
	UsuarioDAO dao;
	
	public UsuarioService()
	{
		dao = new UsuarioDAO();
	}
	
	public Usuario readLogin()
	{
		return dao.readLogin();
	}
	
	
}
