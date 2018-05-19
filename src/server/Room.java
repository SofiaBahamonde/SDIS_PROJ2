package server;

import player.PlayerID;

import java.util.ArrayList;

public class Room {
	String name;
	String password;
	PlayerID owner;
	
	ArrayList<PlayerID> players = new ArrayList<PlayerID>();
	
	public Room(String name, String password,PlayerID owner) {
		this.name = name;
		this.password = password;
		this.owner = owner;
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
	
	public boolean isPrivate() {
		if(password.equals("")) return false;
		return true;
	}

	public String getPassword() {
		return password;
	}

	public void addPlayer(PlayerID player) {
		players.add(player);
	}
}
