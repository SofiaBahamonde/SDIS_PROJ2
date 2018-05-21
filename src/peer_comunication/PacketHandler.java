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
import utils.SecretKeyGenerator;

public class PacketHandler  implements Runnable{


	private DatagramPacket packet;
	private String[] header_split;
	private String content;
	private SecretKey secretKey;
	DesEncrypter encrypter;

	public PacketHandler(DatagramPacket packet,SecretKey secretKey) {
		this.packet = packet;
		this.secretKey= secretKey;
		
		try {
			this.encrypter= new DesEncrypter(secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		MessageExtractor();
		switch(header_split[0]) {
		case "NEWPLAYER":
			NEWPLAYER_handler();
			break;
		
		case "BLACKCARD":
			break;
			
		case "WHITECARD":
			break;
			
		case "PICKWHITECARD":
			break;
			
		case "NEWJUDGE":
			break;
			
		
		}

}

	private void NEWPLAYER_handler() {
		System.out.println(content + " has joinded the room");
		
	}

	private void MessageExtractor() {
		ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
		try {
			String message = bufferedReader.readLine();
			message= this.encrypter.decrypt(message);

			String[] parts = message.split(Message.CRLF);
			this.header_split=parts[0].split("\\s+");
			this.content=parts[1];
		
		} catch (IOException e) {
		e.printStackTrace();
	}
	}



}
