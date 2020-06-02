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

public class InserirUsuarioMain 
{
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, ClassNotFoundException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		/*InserirUsuario inserirUsuario = new InserirUsuario();
		inserirUsuario.inserirUsuario();*/
		
		Usuario usuario = new Usuario();
		UsuarioService usuarioService = new UsuarioService();
		
		/*AlgoritmoAES algoritmoAES = new AlgoritmoAES();
		algoritmoAES.geradorDeChave();*/
		
		usuario = usuarioService.readLoginCripto();
		System.out.println(usuario.getNome());
		System.out.println(usuario.getLogin());
		System.out.println(usuario.getSenhaCripto());
	}
}
