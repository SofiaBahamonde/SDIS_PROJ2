package peer_comunication;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import utils.DesEncrypter;

public class PacketHandler  implements Runnable{


	private DatagramPacket packet;
	private String[] header_split;
	private String content;
	private String message;
	private SecretKey secretKey;
	DesEncrypter encrypter;

	public PacketHandler(DatagramPacket packet,SecretKey secretKey) {
		this.packet = packet;
		this.secretKey= secretKey;
		System.out.println(packet);
		try {
			this.encrypter= new DesEncrypter(secretKey);
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

	public void run() {
		MessageExtractor();
		System.out.println(message);
		System.out.println("STRING DIVIDED "+header_split[0]+ " "+header_split[1]+" MESSAGE:"+this.content);

}

	private void NEWPLAYER_handler() {
		// TODO Auto-generated method stub
		
	}

	private void MessageExtractor() {
		System.out.println("EXTRACTING MESSAGE");
		ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
		try {
			String message = bufferedReader.readLine();
			String ola =SecretKeyGenerator.keyToString(secretKey);
			System.out.println("KEY "+ola);
			System.out.println("ENCRYPTED "+message);
			message= this.encrypter.decrypt(message);
			System.out.println("DESENCRYPTED "+message);
			String[] parts = message.split(Message.CRLF);
			this.header_split=parts[0].split("\\s+");
			this.content=parts[1];
			System.out.println("STRING DIVIDED "+parts[0]+" "+parts[1]);
			
		} catch (IOException e) {
		e.printStackTrace();
	}
	}



}
