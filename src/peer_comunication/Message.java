package peer_comunication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import player.Player;

public class Message {
	
	public static final String CRLF = "\r"+"\n";
	
	public static void BLACKCARD(String content,int senderID,SecretKey secretKey) {
		String header="BLACKCARD"+" "+senderID+ " "+CRLF;
		String message=header + content;
		System.out.println("MESSAGE: "+message);
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			System.out.println("ENCRYPTED MESSAGE: "+message);
			byte[] packet=message.getBytes();
			Player.getDispatcher().sendMessage(packet);
			
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
		
	}
	
	public static void WHITECARD(String content,int senderID,SecretKey secretKey) {
		String header="WHITECARD"+" "+senderID+ " "+CRLF;
		String message=header + content;
		System.out.println("MESSAGE: "+message);
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			System.out.println("ENCRYPTED MESSAGE: "+message);
			byte[] packet=message.getBytes();
			Player.getDispatcher().sendMessage(packet);
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	
	}
	public static void PICKWHITECARD(String content,int senderID,SecretKey secretKey) {
		String header="PICKWHITECARD"+" "+senderID+ " "+CRLF;
		String message=header + content;
		System.out.println("MESSAGE: "+message);
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			System.out.println("ENCRYPTED MESSAGE: "+message);
			byte[] packet=message.getBytes();
			Player.getDispatcher().sendMessage(packet);
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

}
