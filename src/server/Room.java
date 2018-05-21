package server;

import player.PlayerInfo;
import utils.SecretKeyGenerator;
import utils.Utils;

import java.util.ArrayList;

import javax.crypto.SecretKey;

import game.GameLogic;
import peer_comunication.MessageDispatcher;

public class Room implements Runnable{
	private static int room_counter=0;
	
	// base fields
	private String name;
	private String password;
	private PlayerInfo owner;
	private ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
	
	// communication fields
	private String mcast_addr;
	private SecretKey secret_key;
	private int port;
	
	// game fields
	private GameLogic game;
	private MessageDispatcher dispatcher;
	
	
	public Room(String name, String password,PlayerInfo owner) {
		this.name = name;
		this.password = password;
		this.owner = owner;
		
		players.add(owner);
		
		this.mcast_addr = Utils.addresses.get(room_counter);
		this.port = Utils.ports.get(room_counter);
		this.secret_key =SecretKeyGenerator.generateSecretKey();
		 

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
		return mcast_addr;
	}
	
	public String getKey() {
		return SecretKeyGenerator.keyToString(secret_key);
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

		dispatcher = new MessageDispatcher(port, mcast_addr, secret_key,-1);
		
		game = new GameLogic("../black_cards.txt", "../white_cards.txt");
		
		sendWhiteCard();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int round=0;
		int jury =0;
	//	while (round <MAX_ROUNDS) {
		String black_card=game.drawBlackCard();
		dispatcher.sendMessage("BLACKCARD", black_card, -1);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		dispatcher.sendMessage("NEWJUDGE", "judge", players.get(jury).getPlayerID());		
		if(jury != players.size())
			jury++;
		else
			jury =0;
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		dispatcher.sendMessage("START_ROUND", "round", -1);
			*/

		//}
		
	}

	private void sendWhiteCard() {
		//INITIAL CARDS FOR EACH PLAYER
		for (int i = 0; i < players.size(); i++) {
			String initial_cards = game.getWhiteCards(5);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			dispatcher.sendMessage("INITIALCARDS",initial_cards, players.get(i).getPlayerID());
			
		}	
	}
	
}
