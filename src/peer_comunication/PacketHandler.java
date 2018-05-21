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
import utils.Utils;


public class PacketHandler  implements Runnable{


	private DatagramPacket packet;
	private String[] header_split;
	private String content;
	private int sender_id;
	DesEncrypter encrypter;
	private int player_id;

	public PacketHandler(DatagramPacket packet,SecretKey secret_key, int player_id) {
		this.packet = packet;
		this.player_id = player_id;
		
		try {
			this.encrypter= new DesEncrypter(secret_key);
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
			
		case "START_ROUND":
			STARTROUND_handler();
			break;
			
		case "WHITECARD":
			break;
			
		case "PICKWHITECARD":
			break;
			
		case "NEWJUDGE":
			NEWJUDGE_handler();
			break;
		
		case "INITIALCARDS":
			INITIALCARDS_handler();
			break;
			
		
		}
		

}

	private void STARTROUND_handler() {
		Player.startRound();
		
	}


	private void NEWJUDGE_handler() {
		if(player_id == sender_id)
			Player.isJury(true);
		
	}


	private void NEWPLAYER_handler() {
		System.out.println(content + " has joinded the room");

	}
	
	private void BLACKCARD_handler() {
		Player.setBlackCard(content);		
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
		
		byte[] buffer = packet.getData();
		String message =new String(buffer, 0, packet.getLength());
		

		
		message = this.encrypter.decrypt(message);
		
		
		String[] parts = message.split(Utils.CRLF);
		this.header_split=parts[0].split("\\s+");
		this.sender_id=Integer.parseInt(header_split[1]);
		this.content=parts[1];
		
		
	}



}
