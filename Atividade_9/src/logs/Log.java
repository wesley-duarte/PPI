package logs;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import model.Usuario;

public class Log 
{
	public void registrarLog(Usuario usuario) throws IOException
	{
		Calendar calendar = Calendar.getInstance();
		File file = new File("C:\\Users\\-` A n a ´-\\Desktop\\6º Semestre\\Praticas de Programação Integrada\\Laboratorio\\Aula 09\\aula09_PPI\\WebContent\\log","log.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file, true);
		DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
		PrintWriter outStream = new PrintWriter(dataOutputStream);
		
		outStream.println("Usuario logado: "+usuario.getLogin()+" - "+usuario.getNome()+"\nData e Hora: "+calendar.getTime());
		outStream.println("___________________________________________________________________________________________");
		
		outStream.flush();
		outStream.close();
	}
}
