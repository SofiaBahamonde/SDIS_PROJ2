package communication;

import java.net.DatagramPacket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import game.GameLogic;
import player.Player;
import player.PlayerInfo;
import server.Room;
import utils.AESEncrypter;
import utils.Utils;


public class PacketHandler  implements Runnable{


	private DatagramPacket packet;
	private String[] header_split;
	private String content;
	private int sender_id;
	AESEncrypter encrypter;
	private int player_id;

	public PacketHandler(DatagramPacket packet,SecretKey secret_key, int player_id) {
		this.packet = packet;
		this.player_id = player_id;
		
		try {
			this.encrypter= new AESEncrypter(secret_key);
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
							
			case "READY":
				READY_handler();
				break;
							
			case "START":
				START_handler();
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
				PICKWHITECARD_handler();
				break;
				
			case "NEWJUDGE":
				NEWJUDGE_handler();
				break;
			
				
			case "ROUNDWINNER":
				ROUNDWINNER_handler();
				break;
			
			case "ROUNDEND":
				ROUNDEND_handler();
				break;
			
			case "GAME_END":
				GAME_END_handler();
				break;

		}
		

	}
	
	private void GAME_END_handler() {
		if(Player.isOwner()) {
			Player.endGame();
		}
		
	}


	private void NEWPLAYER_handler() {
		System.out.println(content + " has joinded the room.");
		
		if(Player.isOwner()) 
			GameLogic.addNewPlayer(new PlayerInfo(content,sender_id));
	}

	private void READY_handler() {
		if(player_id != sender_id) 
		System.out.println(content + " is ready.");
		
		if(Player.isOwner()) 
			GameLogic.playerReady();
	}
	
	private void START_handler() {
		System.out.println("GAME HAS STARTED");
		Player.getInitialCards();
	}
	
	private void BLACKCARD_handler() {
		Player.setBlackCard(content);		
	}


	private void ROUNDEND_handler() {
		if(!Player.isJury()) {
			Player.drawWhiteCard();
		}
		else {
			Player.endJury();
		}
		if(Player.isOwner()) {
			GameLogic.round();
		}
	}


	private void ROUNDWINNER_handler() {
		if(player_id!=-1) {
		if(player_id == sender_id) {
			Player.setPoints();
			System.out.println(content);
		}
		
	}
	}


	private void STARTROUND_handler() {
		Player.startRound();
		
	}


	private void NEWJUDGE_handler() {
		if(player_id!=-1) {
		if(player_id == sender_id)
			Player.isJury(true);
		
	}
	}


	
	
	private void PICKWHITECARD_handler() {
		if(player_id!=-1) {
		if(Player.isJury()) {
			Player.addAnswer(content,sender_id);
		}
		
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
