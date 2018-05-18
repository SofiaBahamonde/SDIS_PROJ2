package server;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.net.ssl.*;

import utils.Utils;
import player.PlayerID;

public class Server{
	
	static ArrayList<PlayerID> players = new ArrayList<PlayerID>();
	
    public static void main(String[] args) throws Exception {

    SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(Utils.PORT);
    
    ss.setNeedClientAuth(true);
    ss.setEnabledCipherSuites(ssf.getSupportedCipherSuites());


    
    while(true) {
      SSLSocket socket = (SSLSocket) ss.accept();
      new Thread( new Handler(socket)).start();
    }


    //s.close();

    }
    
	public static void addPlayer(PlayerID player) {
		players.add(player);
		
	}

	public static ArrayList<PlayerID> getPlayers() {
		return players;
	}


}

