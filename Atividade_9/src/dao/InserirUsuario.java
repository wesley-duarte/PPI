package dao;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import cryptography.AlgoritmoAES;
import model.Usuario;
import service.UsuarioService;

public class InserirUsuario 
{
	AlgoritmoAES algoritmoAES = new AlgoritmoAES();
	UsuarioService usuarioService = new UsuarioService();
	
	public void inserirUsuario() throws NoSuchAlgorithmException, IOException, InvalidKeyException, ClassNotFoundException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		algoritmoAES.geradorDeChave();
		
		Usuario usuario = new Usuario();
		usuario.setNome("USJT");
		usuario.setLogin("pratprog");
		usuario.setSenha("1234");
		
		byte[] senha = usuario.getSenha().getBytes(); //transforma a senha String em bytes e armazena em senha
		algoritmoAES.geradorDeCifra(senha); //passa o byte senha para o geradorDeCifra
		usuario.setSenhaCripto(algoritmoAES.getTextoCifrado());
		//String senhaDesifrada = (new String(textoCifrado, "ISO-8859-1"));;
		
		usuarioService.create(usuario);
	}
	
}
