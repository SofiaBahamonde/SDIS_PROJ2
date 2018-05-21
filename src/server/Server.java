package server;

import java.util.ArrayList;
import javax.net.ssl.*;

import utils.Utils;
import player.PlayerInfo;

public class Server{
	

	static ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	static ArrayList<Room> rooms = new ArrayList<Room>();
	
	
    public static void main(String[] args) throws Exception {

	    SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
	    SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(Utils.PORT);
	    
	    ss.setNeedClientAuth(true);
	    ss.setEnabledCipherSuites(ssf.getSupportedCipherSuites());
	
	    while(true) {
	      SSLSocket socket = (SSLSocket) ss.accept();
	      new Thread( new ServerHandler(socket)).start();
	    }


    }
    
	public static void addPlayer(PlayerInfo player) {
		System.out.println("New player has join the server - " + player.toString());
		players.add(player);
		
	}
	
	public static void addRoom(String name, String password, PlayerInfo owner) {
		System.out.println("New room created - " + name);
		rooms.add(new Room(name, password,owner));	
	}

	public static ArrayList<PlayerInfo> getPlayers() {
		return players;
	}

	public static ArrayList<Room> getRooms() {
		return rooms;
	}

	public static Room getRoom(String room_name) {
		for(Room r: rooms) {
			if(r.getName().equals(room_name));
			return r;
		}
		
		return null;
	}

	public static void joinRoom(PlayerInfo player, Room room) {
		room.addPlayer(player);
		System.out.println("Player has joined room " + room.getName() + " - " + player.toString());
		
	}


}

