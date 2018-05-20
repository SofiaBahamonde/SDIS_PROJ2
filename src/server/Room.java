package server;

import player.PlayerID;
import utils.Utils;

import java.util.ArrayList;

import javax.crypto.SecretKey;

import peer_comunication.SecretKeyGenerator;

public class Room {
	private static int room_counter=0;
	
	// base fields
	private String name;
	private String password;
	private PlayerID owner;
	private ArrayList<PlayerID> players = new ArrayList<PlayerID>();
	
	// communication fields
	private String address;
	private String secret_key;
	private int port;
	
	
	
	public Room(String name, String password,PlayerID owner) {
		this.name = name;
		this.password = password;
		this.owner = owner;
		
		this.address = Utils.addresses.get(room_counter);
		this.port = Utils.ports.get(room_counter);
		
		SecretKey secretKey=SecretKeyGenerator.generateSecretKey();
		this.secret_key =SecretKeyGenerator.keyToString(secretKey);

		
	}
	
	@Override
	public String toString() {
		String str ="";
		
		str += "--------------------------------------\n"; 
		
		str += name + "\n";
		str += "type: " + (isPrivate() ? "private" : "public") + "\n";
		str += "owner: " + owner.toString() + "\n";
		
		str += "players:\n";
		for(PlayerID p:players) 
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


	public void addPlayer(PlayerID player) {
		players.add(player);
	}
}
