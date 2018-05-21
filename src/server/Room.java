package server;

import player.PlayerInfo;
import utils.SecretKeyGenerator;
import utils.Utils;

import java.util.ArrayList;

import javax.crypto.SecretKey;

import game.GameLogic;

public class Room implements Runnable{
	private static int room_counter=0;
	
	// base fields
	private String name;
	private String password;
	private PlayerInfo owner;
	private ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	private GameLogic game;
	
	// communication fields
	private String address;
	private String secret_key;
	private int port;
	
	
	
	public Room(String name, String password,PlayerInfo owner) {
		this.name = name;
		this.password = password;
		this.owner = owner;
		
		this.address = Utils.addresses.get(room_counter);
		this.port = Utils.ports.get(room_counter);
		
		SecretKey secretKey=SecretKeyGenerator.generateSecretKey();
		this.secret_key =SecretKeyGenerator.keyToString(secretKey);
		
		room_counter++;

		
	}
	
	@Override
	public String toString() {
		String str ="";
		
		str += "--------------------------------------\n"; 
		
		str += name + "\n";
		str += "type: " + (isPrivate() ? "private" : "public") + "\n";
		str += "owner: " + owner.toString() + "\n";
		
		str += "players:\n";
		for(PlayerInfo p:players) 
			str += "  " + p.toString()+ "\n";
		
		str += "--------------------------------------\n"; 
		
		return str;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	public String getKey() {
		return secret_key;
	}
	
	public String getPort() {
		return Integer.toString(port);
	}

	
	public boolean isPrivate() {
		if(password.equals("")) return false;
		return true;
	}


	public void addPlayer(PlayerInfo player) {
		players.add(player);
	}

	@Override
	public void run() {
		System.out.println("Game has started - room " + name);
		game=new GameLogic("black_cards.txt","white_cards.txt");
		
		while(true) {
			
		}
		
	}
}
