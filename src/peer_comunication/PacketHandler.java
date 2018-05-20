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
	private String[] headerToken;
	private String header;
	private String message;
	private SecretKey secretKey;
	DesEncrypter encrypter;

	public PacketHandler(DatagramPacket packet,SecretKey secretKey) {
		this.packet = packet;
		this.secretKey= secretKey;
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
		String message=MessageExtractor();
		System.out.println(message);

}

	private void NEWPLAYER_handler() {
		// TODO Auto-generated method stub
		
	}

	private String MessageExtractor() {
		ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
		try {
			String message = bufferedReader.readLine();
			message= this.encrypter.decrypt(message);
			return message;
		} catch (IOException e) {
		e.printStackTrace();
	}
		return null;
	}

	public String[] Message2Extractor() {
		ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

		try {
			this.header = bufferedReader.readLine();
			System.out.println(this.header);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.header.split(("\\s+"));
}

}
