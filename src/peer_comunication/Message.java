package peer_comunication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Message {
	
	public static final String CRLF = "\r"+"\n";
	
	public static void BLACKCARD(String content,int senderID,SecretKey secretKey) {
		String header="BLACKCARD"+" "+senderID+ " "+CRLF +CRLF;
		String message=header + content;
		System.out.println("MESSAGE: "+message);
		try {
			DesEncrypter des= new DesEncrypter(secretKey);
			message=des.encrypt(message);
			System.out.println("ENCRYPTED MESSAGE: "+message);
			byte[] packet=message.getBytes();
			
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

}
