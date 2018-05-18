package server;

import java.util.ArrayList;

import javax.net.ssl.*;

import utils.Utils;
import player.PlayerID;

public class Server{
	

	static ArrayList<PlayerID> players = new ArrayList<PlayerID>();
	static ArrayList<Room> rooms = new ArrayList<Room>();
	
    public static void main(String[] args) throws Exception {

    SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(Utils.PORT);
    
    ss.setNeedClientAuth(true);
    ss.setEnabledCipherSuites(ssf.getSupportedCipherSuites());

    while(true) {
      SSLSocket socket = (SSLSocket) ss.accept();
      new Thread( new PlayerHandler(socket)).start();
    }


    }
    
	public static void addPlayer(PlayerID player) {
		players.add(player);
		
	}
	
	public static void addRoom(String name, String password, PlayerID owner) {
		rooms.add(new Room(name, password,owner));	
	}

	public static ArrayList<PlayerID> getPlayers() {
		return players;
	}

	public static ArrayList<Room> getRooms() {
		return rooms;
	}

	


}

