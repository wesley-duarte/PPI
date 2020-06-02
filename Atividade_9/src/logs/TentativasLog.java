package logs;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import model.Usuario;

public class TentativasLog 
{
	Usuario usuario = new Usuario();
	public void registrarTentadivasLogSenhaErrada(String login, String senha) throws IOException
	{
		Calendar calendar = Calendar.getInstance();
		File file = new File("C:\\Users\\-` A n a ´-\\Desktop\\6º Semestre\\Praticas de Programação Integrada\\Laboratorio\\Aula 09\\aula09_PPI\\WebContent\\log","tentativas de log.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file, true);
		DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
		PrintWriter outStream = new PrintWriter(dataOutputStream);

		outStream.println("Tentativa de Login: "+usuario.getLogin()+" - "+usuario.getNome()+"\nData e Hora: "+calendar.getTime()+"\nSenha invalida");
		outStream.println("___________________________________________________________________________________________");
		
		outStream.flush();
		outStream.close();
	}
	public void registrarTentadivasLogUsuarioInvalido(String login, String senha) throws IOException
	{
		Calendar calendar = Calendar.getInstance();
		File file = new File("C:\\Users\\-` A n a ´-\\Desktop\\6º Semestre\\Praticas de Programação Integrada\\Laboratorio\\Aula 08\\aula08_PPI\\WebContent\\log","tentativas de log.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file, true);
		DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
		PrintWriter outStream = new PrintWriter(dataOutputStream);

		outStream.println("Tentativa de Login: "+login+"\nData e Hora: "+calendar.getTime()+"\nUsuario Invalido");
		outStream.println("___________________________________________________________________________________________");
		
		outStream.flush();
		outStream.close();
	}
	
}
