package peer_comunication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import utils.DesEncrypter;

public class Message {
	
	public static final String CRLF = "\r"+"\n";
	
	public static byte[] BLACKCARD(String content,int senderID,SecretKey secretKey) {
		String header="BLACKCARD"+" "+senderID+CRLF;
		String message=header + content;
		System.out.println("MESSAGE: "+message);
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			System.out.println("ENCRYPTED MESSAGE: "+message);
			byte[] packet=message.getBytes();
			return packet;
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	public static byte[] WHITECARD(String content,int senderID,SecretKey secretKey) {
		String header="WHITECARD"+" "+senderID+CRLF;
		String message=header + content;
		System.out.println("MESSAGE: "+message);
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			System.out.println("ENCRYPTED MESSAGE: "+message);
			byte[] packet=message.getBytes();
			return packet;
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
	
	}


	public static byte[] PICKWHITECARD(String content,int senderID,SecretKey secretKey) {
		String header="PICKWHITECARD"+" "+senderID+CRLF;
		String message=header + content;
		System.out.println("MESSAGE: "+message);
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			System.out.println("ENCRYPTED MESSAGE: "+message);
			byte[] packet=message.getBytes();
			return packet;
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static byte[] NEWPLAYER(String content,int senderID,SecretKey secretKey) {
		String header="NEWPLAYER"+" "+senderID+CRLF;
		String message=header + content;
		
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			byte[] packet=message.getBytes();
			return packet;
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static byte[] NEWJUDGE(String content,int senderID,SecretKey secretKey) {
		String header="NEWJUDGE"+" "+senderID+CRLF;
		String message=header + content;
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			byte[] packet=message.getBytes();
			return packet;
			
		} catch (InvalidKeyException e) {
			
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static byte[] INITIALCARDS(String content,int senderID,SecretKey secretKey) {
		String header="INITIALCARDS"+" "+senderID+CRLF;
		String message=header + content;

		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);

			byte[] packet=message.getBytes();
			return packet;
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
	
	}

}
