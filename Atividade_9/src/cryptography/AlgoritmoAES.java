package cryptography;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AlgoritmoAES 
{
	private byte[] textoCifrado;
	private byte[] textoDecifrado;
	
	public AlgoritmoAES()
	{
		textoCifrado = null;
		textoDecifrado = null;
	}
	
	public void geradorDeChave() throws NoSuchAlgorithmException, IOException
	{
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		
		SecretKey secretKey = keyGenerator.generateKey();
		
		File file = new File("chave.dat");
		if(file.exists() != true)
		{
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(secretKey);
			objectOutputStream.close();
			System.out.println("Chave criada");
		}
		else
		{
			System.out.println("A chave ja existe");
		}
	}
	
	public void geradorDeCifra(byte[] senha) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		File file = new File("C:\\Users\\-` A n a ´-\\Desktop\\6º Semestre\\Praticas de Programação Integrada\\Laboratorio\\Aula 09\\aula09_PPI", "chave.dat");
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		SecretKey secretKey = (SecretKey) objectInputStream.readObject();
		byte[] chave = secretKey.getEncoded();
		objectInputStream.close();
		
		Cipher cipherAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]);
		cipherAES.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"), ivParameterSpec);
		textoCifrado = cipherAES.doFinal(senha);
	}
	
	public void geradorDeDecifra(byte[] senha) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		File file = new File("chave.dat");
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		SecretKeySpec secretKeySpec = (SecretKeySpec) objectInputStream.readObject();
		objectInputStream.close();
		
		Cipher cipherAES = Cipher.getInstance("AES/CBC/PKS5Padding");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]);
		cipherAES.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		textoDecifrado = cipherAES.doFinal(senha);
	}

	public byte[] getTextoCifrado() 
	{
		return textoCifrado;
	}

	public byte[] getTextoDecifrado() 
	{
		return textoDecifrado;
	}

}
