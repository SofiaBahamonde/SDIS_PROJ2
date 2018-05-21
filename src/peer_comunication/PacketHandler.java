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

import player.Player;
import utils.DesEncrypter;


public class PacketHandler  implements Runnable{


	private DatagramPacket packet;
	private String[] header_split;
	private String content;
	private SecretKey secretKey;
	private int sender_id;
	DesEncrypter encrypter;
	private int player_id;

	public PacketHandler(DatagramPacket packet,SecretKey secretKey, int player_id) {
		this.packet = packet;
		this.secretKey= secretKey;
		this.player_id = player_id;
		
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
			BLACKCARD_handler();
			break;
			
		case "WHITECARD":
			break;
			
		case "PICKWHITECARD":
			PICKWHITECARD_handler();
			break;
			
		case "NEWJUDGE":
			break;
		
		case "INITIALCARDS":
			INITIALCARDS_handler();
			break;
			
		
		}
		

}

	private void PICKWHITECARD_handler() {

		
	}

	private void NEWPLAYER_handler() {
		System.out.println(content + " has joinded the room");

	}
	
	private void BLACKCARD_handler() {
		System.out.println("NEW BLACK_CARD"+content);
		
	}

	
	private void INITIALCARDS_handler() {
		if(player_id == sender_id) {		
			String[] cards;
			cards= content.split("_");
			
			for(int i=0; i<cards.length;i++) {
				Player.addWhiteCard(cards[i]);
			}
			
			Player.showWhiteCards();
		
		}
		
	}
	private void MessageExtractor() {
		ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
		
		String message = "";
		try {
			message = bufferedReader.readLine();
			message= this.encrypter.decrypt(message);

			String[] parts = message.split(Message.CRLF);
			this.header_split=parts[0].split("\\s+");
			this.sender_id=Integer.parseInt(header_split[1]);
			this.content=parts[1];
		
		} catch (IOException e) {
		e.printStackTrace();
	}
	}



}
