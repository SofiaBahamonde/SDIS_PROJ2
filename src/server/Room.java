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
}
